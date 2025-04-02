package br.com.gallodev.filmix.ui.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.gallodev.filmix.ui.data.model.FavoriteFilm
import br.com.gallodev.filmix.ui.presentation.favoriteActivity.FavoriteFilmActivity
import java.time.Instant

@Database(entities = [FavoriteFilm::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao

    companion object { // Objeto de companion para obter a instância do banco de dados
        private var INSTAMCE: AppDataBase? = null
        // Singleton para garantir que apenas uma instância do banco de dados seja criada
        fun getDatabase(context: Context): AppDataBase {
            return INSTAMCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "favorite_movies"
                ).build()
                INSTAMCE = instance
                instance
            }
        }
    }
}