package br.com.gallodev.filmix.ui.data.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("runtime") val runtime: Int, // Tempo de duração em minutos
    @SerializedName("genres") val genres: List<Genre>, // Gêneros do filme
    @SerializedName("vote_average") val voteAverage: Double?, // Média de votos do filme
    @SerializedName("release_date") val releaseDate: String, // Data de lançamento do filme
    @SerializedName("reviews") val reviews: ReviewResponse?,
    @SerializedName("author_details") val authorDetails: ReviewResponse?,
    @SerializedName("credits") val credits: CastResponse?
) : Parcelable {
    @Parcelize
    data class Genre(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String
    ) : Parcelable

    @Parcelize
    data class ReviewResponse(
        @SerializedName("results") val reviews: List<Review>
    ) : Parcelable

    @Parcelize
    data class Review(
        @SerializedName("author") val author: String,
        @SerializedName("content") val content: String
    ) : Parcelable

    @Parcelize
    data class CastResponse(
        @SerializedName("cast") val cast: List<CastMember>
    ) : Parcelable

    @Parcelize
    data class CastMember(
        @SerializedName("original_name") val name: String,
        @SerializedName("character") val character: String,
        @SerializedName("profile_path") val profilePath: String
    ) : Parcelable

    val imageUrl get() = "https://image.tmdb.org/t/p/w500/$poster"
}


