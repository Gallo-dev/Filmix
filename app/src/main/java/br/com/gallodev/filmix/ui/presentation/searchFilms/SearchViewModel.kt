package br.com.gallodev.filmix.ui.presentation.searchFilms

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gallodev.filmix.ui.data.api.Film
import br.com.gallodev.filmix.ui.data.api.FilmService
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse
import br.com.gallodev.filmix.ui.data.api.ResponseFilm
import br.com.gallodev.filmix.ui.data.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    private val _films = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>> = _films

    private val _movieDetails = MutableLiveData<MovieDetailResponse>()
    val movieDetails: LiveData<MovieDetailResponse> = _movieDetails


    private val filmService: FilmService = RetrofitClient.filmService
    private val apiKey = "b893de681882461ea6ffc92edc5a3dfd"
    private val language = "pt-BR"

    fun searchFilm(query: String) {
        filmService.searchMovie(apiKey,query,language)
            .enqueue(object : Callback<ResponseFilm>{
                override fun onResponse(
                    call: Call<ResponseFilm>,
                    response: Response<ResponseFilm>
                ) {
                   if (response.isSuccessful){
                       _films.value = response.body()?.films ?: emptyList()
                   }else {
                       _films.value = emptyList()
                   }
                }
                override fun onFailure(call: Call<ResponseFilm>, t: Throwable) {
                    _films.value = emptyList()
                }
            })
    }

    fun fetchMovieDetails(movieId: Int) { // Método para buscar detalhes do filme
        val apiService = RetrofitClient.retrofit.create(FilmService::class.java)
        val call = apiService.getMovieDetails(movieId, "b893de681882461ea6ffc92edc5a3dfd")

        call.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse( // Chamado quando a resposta é recebida com sucesso
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful){ // Verifica se a resposta é bem-sucedida
                    _movieDetails.value = response.body()
                }else { // Caso a resposta não seja bem-sucedida, imprime o erro
                }
            }
            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) { // Chamado se houver um erro na chamada
            }
        })
    }

}