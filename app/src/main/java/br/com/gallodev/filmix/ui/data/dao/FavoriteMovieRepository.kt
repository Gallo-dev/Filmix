package br.com.gallodev.filmix.ui.data.dao

import android.util.Log
import androidx.lifecycle.LiveData
import br.com.gallodev.filmix.ui.data.model.FavoriteFilm

class FavoriteMovieRepository(private val favoriteMovieDao: FavoriteMovieDao) {

    val allFavoriteMovies: LiveData<List<FavoriteFilm>> = favoriteMovieDao.getAllMovies()

    suspend fun insert(favoriteFilm: FavoriteFilm) {
        favoriteMovieDao.insertMovie(favoriteFilm)
        Log.d("FavoriteMovieRepository", "Filme adicionado aos favoritos: $favoriteFilm")
    }

    suspend fun delete(favoriteFilm: FavoriteFilm) {
        favoriteMovieDao.deleteMovie(favoriteFilm)
    }

    suspend fun getFavoriteMovieById(): List<Int> { // Método para obter os IDs dos filmes favoritos
        return favoriteMovieDao.getAllFavoriteMoviesIds()
    }

    suspend fun isFavorite(movieId: Int): Boolean { // Método para verificar se um filme é favorito
        val favoriteMovie = favoriteMovieDao.getFavoriteMovieById(movieId)
        return favoriteMovie != null
    }

}