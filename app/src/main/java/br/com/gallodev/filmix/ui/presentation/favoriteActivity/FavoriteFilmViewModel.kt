package br.com.gallodev.filmix.ui.presentation.favoriteActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import br.com.gallodev.filmix.ui.data.api.FilmService
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse
import br.com.gallodev.filmix.ui.data.api.RetrofitClient
import br.com.gallodev.filmix.ui.data.dao.AppDataBase
import br.com.gallodev.filmix.ui.data.dao.FavoriteMovieRepository
import br.com.gallodev.filmix.ui.data.model.FavoriteFilm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteFilmViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FavoriteMovieRepository
    private val _favoriteMoviesDetails = MutableLiveData<List<MovieDetailResponse>>()
    val favoriteMoviesDetails: LiveData<List<MovieDetailResponse>> = _favoriteMoviesDetails


    init {
        val favoriteMovieDao = AppDataBase.getDatabase(application).favoriteMovieDao()
        repository = FavoriteMovieRepository(favoriteMovieDao)
        fetchFavoriteMovies()
    }

    private fun fetchFavoriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val favoriteMovieIds =
                repository.getFavoriteMovieById() // Obtém os IDs dos filmes favoritos


            if (favoriteMovieIds.isEmpty()) { // Itera sobre os IDs dos filmes favoritos
                _favoriteMoviesDetails.postValue(emptyList()) // Se não houver filmes favoritos, define a lista como vazia
                return@launch
            }
            val movieDetailFilmList = favoriteMovieIds.map { id ->
                try {
                    val response = RetrofitClient.retrofit.create(FilmService::class.java)
                        .getMovieDetails(id, "b893de681882461ea6ffc92edc5a3dfd")
                        .execute()
                    if (response.isSuccessful) response.body() else null
                } catch (e: Exception) {
                    null // Trata exceções ao obter detalhes do filme
                }
            }
            _favoriteMoviesDetails.postValue(movieDetailFilmList as List<MovieDetailResponse>?)
        }
    }

    fun insert(favoriteFilm: FavoriteFilm) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(favoriteFilm)
        fetchFavoriteMovies() // Atualiza a lista de filmes favoritos após a inserção
    }

    fun delete(favoriteFilm: FavoriteFilm) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(favoriteFilm)
        fetchFavoriteMovies() // Atualiza a lista de filmes favoritos após a remoção
    }
}