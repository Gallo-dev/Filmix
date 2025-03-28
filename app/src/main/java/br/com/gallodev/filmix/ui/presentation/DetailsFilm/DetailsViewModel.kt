package br.com.gallodev.filmix.ui.presentation.DetailsFilm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gallodev.filmix.ui.data.api.FilmService
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse
import br.com.gallodev.filmix.ui.data.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel: ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetailResponse>()
    val movieDetails: LiveData<MovieDetailResponse> = _movieDetails

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