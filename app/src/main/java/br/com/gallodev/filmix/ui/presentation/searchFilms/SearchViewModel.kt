package br.com.gallodev.filmix.ui.presentation.searchFilms

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gallodev.filmix.ui.data.api.Film
import br.com.gallodev.filmix.ui.data.api.FilmService
import br.com.gallodev.filmix.ui.data.api.ResponseFilm
import br.com.gallodev.filmix.ui.data.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    private val _films = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>> = _films

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
}