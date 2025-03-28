package br.com.gallodev.filmix.ui.presentation.listFlims


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gallodev.filmix.ui.data.api.Category
import br.com.gallodev.filmix.ui.data.api.Film
import br.com.gallodev.filmix.ui.data.api.FilmService
import br.com.gallodev.filmix.ui.data.api.ResponseFilm
import br.com.gallodev.filmix.ui.data.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFilmViewModel : ViewModel() {

    private val filmService: FilmService by lazy {
        RetrofitClient.filmService
    }

    private val movie = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>> = movie

    private val tenBest = MutableLiveData<List<Film>>()
    val tenBestFilms: LiveData<List<Film>> = tenBest


    // funcao para buscar filmes por categoria
    fun buscaFilmePorCategoria(category: Category) {
        Log.i("ListaFilmesViewModel", "Buscando filmes por categoria: ${category.description}")
        val call = filmService.listaFilmesPorCategorias(
            category.path,
            "b893de681882461ea6ffc92edc5a3dfd",
            "pt-BR"
        )

        // callback para buscar os filmes
        call.enqueue(object : Callback<ResponseFilm> {
            override fun onResponse(call: Call<ResponseFilm>, response: Response<ResponseFilm>) {

                if (response.isSuccessful) {
                    response.body()?.let { responseFilm ->
                        movie.value = responseFilm.films
                        tenBest.value = responseFilm.films.take(10)
                    }
                } else {
                    Log.e("ListaFilmViewModel", "Erro ao buscar filmes: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseFilm>, t: Throwable) {
                Log.e("ListaFilmViewModel", "Erro ao buscar filmes: ${t.message}")
            }
        })
    }

}