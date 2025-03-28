package br.com.gallodev.filmix.ui.data.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseFilm(
    @SerializedName("results") val films: List<Film>
) : Parcelable

@Parcelize
data class Film(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("avatar_path") val avatarPath: String,
    @SerializedName("runtime") val runtime: Int, // Tempo de duração em minutos
    @SerializedName("genres") val genres: List<Genre>? = emptyList(), // Gêneros do filme
    @SerializedName("vote_average") val voteAverage: Float?, // Média de votos do filme
    @SerializedName("release_date") val releaseDate: String, // Data de lançamento do filme
    @SerializedName("review") val review: String,
    @SerializedName("cast") val cast: String,

    ) : Parcelable {
    @Parcelize
    data class Genre(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String
    ) : Parcelable

    val imageUrl get() = "https://image.tmdb.org/t/p/w500/$poster"

    val formattedVote get() = voteAverage?.toInt() ?: "N/A" // Retorna "N/A" se voteAverage for nulo
}
