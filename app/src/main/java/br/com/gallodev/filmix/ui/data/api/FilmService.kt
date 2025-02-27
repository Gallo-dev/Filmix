package br.com.gallodev.filmix.ui.data.api

import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {
    @GET("movie/{category}")
    fun listaFilmesPorCategorias(
        @Path("category") categoria: String, // Categoria dinâmica (ex.: "now_playing", "upcoming", "top_rated", "popular")
        @Query("api_key") apiKey: String, // Chave de API necessária
        @Query("language") language: String, // Idioma dos resultados, ex.: "pt-BR"
    ): Call<ResponseFilm>
}