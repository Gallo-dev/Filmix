package br.com.gallodev.filmix.ui.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.gallodev.filmix.ui.data.model.FavoriteFilm

@Dao
abstract class FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMovie(favoriteFilm: FavoriteFilm)

    @Delete
    abstract suspend fun deleteMovie(favoriteFilm: FavoriteFilm)

    @Query("SELECT * FROM favorite_films")
    abstract fun getAllMovies(): LiveData<List<FavoriteFilm>>

    @Query("SELECT * FROM favorite_films")
    abstract suspend fun getAllFavoriteMoviesIds(): List<Int>


    @Query("SELECT * FROM favorite_films WHERE movieId = :movieId LIMIT 1")
    abstract suspend fun getFavoriteMovieById(movieId: Int): FavoriteFilm?
}