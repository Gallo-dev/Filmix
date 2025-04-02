package br.com.gallodev.filmix.ui.presentation.favoriteActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gallodev.filmix.databinding.ActivityFavoriteFilmBinding
import br.com.gallodev.filmix.ui.presentation.DetailsFilm.DetailsFilmActivity


class FavoriteFilmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteFilmBinding // ViewBinding para o layout
    private lateinit var favoriteViewModel: FavoriteFilmViewModel // ViewModel para interação com os dados
    private lateinit var adapter: FavoriteAdapter // Adaptador para a RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        supportActionBar?.hide()
        binding = ActivityFavoriteFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteViewModel = ViewModelProvider(this)[FavoriteFilmViewModel::class.java]

        setupRecyclerViewFavorite()
        setupOberservers()
        setupBackButton()

    }

    private fun setupRecyclerViewFavorite() {
        adapter = FavoriteAdapter(mutableListOf()) { movie ->
            val intent = Intent(this, DetailsFilmActivity::class.java)
            intent.putExtra("FILM_ID", movie.id)
            startActivity(intent)
        }// Inicializa o adaptador com uma lista vazia

        binding.recyclerViewFavorite.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFavorite.adapter = adapter
    }

    private fun setupOberservers() {
        // Observa as mudanças na lista de filmes favoritos e atualiza o adapter
        favoriteViewModel.favoriteMoviesDetails.observe(this) { favoriteMovies ->
            adapter.updateList(favoriteMovies)
        }
    }

    private fun setupBackButton() {
        binding.icBackFavorite.setOnClickListener {

        }
    }
}