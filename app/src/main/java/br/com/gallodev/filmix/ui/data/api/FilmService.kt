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
        @Query("language") language: String = "en-US" // Idioma dos resultados, ex.: "pt-BR"
    ): Call<ResponseFilm>

        @GET("search/movie")
        fun searchMovie(
            @Query("api_key") apiKey: String, // Chave de API necessária
            @Query("query") query: String, // Nome do filme a ser buscado")
            @Query("language") language: String = "en-US", // Idioma dos resultados, ex.: "pt-BR"
        ): Call<ResponseFilm>

        @GET("movie/{movie_id}")
        fun getMovieDetails(
            @Path("movie_id") movieId: Int, // ID do filme
            @Query("api_key") apiKey: String, // Chave de API necessária
            @Query("language") language: String = "en-US", // Idioma dos resultados, ex.: "pt-BR"
            @Query("append_to_response") appendToResponse: String = "reviews,credits,author_details" // Opções adicionais para resposta
        ): Call<MovieDetailResponse>

}