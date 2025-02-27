package br.com.gallodev.filmix.ui.data.api

import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {
    @GET("movie/top_rated")
    fun listaFilmesPorCategorias(
        @Query("api_key") apiKey: String,
        @Query("languege") language: String,
        @Query("categorias") categoria: String,
    ): Call<ResponseFilm>
}