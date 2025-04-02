package br.com.gallodev.filmix.ui.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_films")
data class FavoriteFilm(
    @PrimaryKey val movieId: Int
)
