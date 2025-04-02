package br.com.gallodev.filmix.ui.presentation.DetailsFilm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.gallodev.filmix.ui.data.api.FilmService
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse
import br.com.gallodev.filmix.ui.data.api.RetrofitClient
import br.com.gallodev.filmix.ui.data.dao.AppDataBase
import br.com.gallodev.filmix.ui.data.dao.FavoriteMovieRepository
import br.com.gallodev.filmix.ui.data.model.FavoriteFilm
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(application: Application) :
    AndroidViewModel(application) { // ViewModel para detalhes do filme

    private val repository = RetrofitClient // Repositório para obter detalhes do filme
    private val _movieDetails = MutableLiveData<MovieDetailResponse>()
    val movieDetails: LiveData<MovieDetailResponse> = _movieDetails
    private val favoriteRepository: FavoriteMovieRepository // Repositório para filmes favoritos


    fun fetchMovieDetails(movieId: Int) { // Método para buscar detalhes do filme
        val apiService = RetrofitClient.retrofit.create(FilmService::class.java)
        val call = apiService.getMovieDetails(movieId, "b893de681882461ea6ffc92edc5a3dfd")

        call.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse( // Chamado quando a resposta é recebida com sucesso
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) { // Verifica se a resposta é bem-sucedida
                    _movieDetails.value = response.body()
                } else { // Caso a resposta não seja bem-sucedida, imprime o erro
                }
            }

            override fun onFailure(
                call: Call<MovieDetailResponse>,
                t: Throwable
            ) { // Chamado se houver um erro na chamada
            }
        })
    }

    init { // Inicializa o repositório de filmes favoritos
        val favoriteMovieDao = AppDataBase.getDatabase(application).favoriteMovieDao()
        favoriteRepository = FavoriteMovieRepository(favoriteMovieDao)
    }

    // Método para adicionar um filme aos favoritos
    fun favoriteMovie(movieId: Int) = viewModelScope.launch {
        val favoriteFilm = FavoriteFilm(movieId)
        favoriteRepository.insert(favoriteFilm)
    }


}