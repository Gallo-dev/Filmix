package br.com.gallodev.filmix.ui.data.api

import br.com.gallodev.filmix.ui.data.model.Filme
import com.google.gson.annotations.SerializedName

data class ResponseFilm(
    @SerializedName("results")val films: List<Film>
)
data class Film(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("overview") val description: String,
    @SerializedName("poster_path") val poster: String,

){
    val  imageUrl get() = "https://image.tmdb.org/t/p/w500/$poster"
}
