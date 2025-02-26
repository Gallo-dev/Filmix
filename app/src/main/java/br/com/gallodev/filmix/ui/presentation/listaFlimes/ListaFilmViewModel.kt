package br.com.gallodev.filmix.ui.presentation.listaFlimes


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gallodev.filmix.ui.data.api.Film
import br.com.gallodev.filmix.ui.data.api.FilmService
import br.com.gallodev.filmix.ui.data.api.ResponseFilm
import br.com.gallodev.filmix.ui.data.api.StartRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaFilmViewModel :ViewModel() {

    private val filmService: FilmService by lazy {
        StartRetrofit().filmService()
    }
    private val move = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>> = move

    private val tenBest = MutableLiveData<List<Film>>()
    val tenBestFilms: LiveData<List<Film>> = tenBest

    fun buscaFilm(){
        Log.i("ListaFilmesViewModel", "Iniciando busca de filmes...")
        val call = filmService.listPupolares("b893de681882461ea6ffc92edc5a3dfd","pt-BR")

        call.enqueue(object : Callback<ResponseFilm>{
            override fun onResponse(call: Call<ResponseFilm>, response: Response<ResponseFilm>) {
                Log.i("ListaFilmesViewModel", "Resposta recebida: ${response.code()}")
                if (response.isSuccessful){
                    val responseFilm = response.body()
                    responseFilm?.let {
                        Log.i("ListaFilmesViewModel", "Filmes encontrados: ${it.films.size}")
                        move.value = it.films
                        tenBest.value = it.films.take(10)
                    }
                }else{
                    Log.e("ListaFilmViewModel", "Erro ao buscar filmes: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseFilm>, t: Throwable) {
                Log.e("ListaFilmViewModel", "Erro ao buscar filmes: ${t.message}")
            }
        })
    }
}