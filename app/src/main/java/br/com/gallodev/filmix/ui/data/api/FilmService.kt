package br.com.gallodev.filmix.ui.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmService {
    @GET("movie/popular")
    fun listPupolares(
        @Query("api_key") apiKey: String,
        @Query("languege") language: String
    ): Call<ResponseFilm>
}