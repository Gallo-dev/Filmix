package br.com.gallodev.filmix.ui.presentation.DetailsFilm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.gallodev.filmix.databinding.ActivityDetailsFilmBinding
import br.com.gallodev.filmix.ui.presentation.listFlims.ListFilmsActivity
import br.com.gallodev.filmix.ui.presentation.searchFilms.SearchListActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator

@Suppress("DEPRECATION")
class DetailsFilmActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsFilmBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsFilmBinding.inflate(layoutInflater)
        //enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]


        val filmId = intent.getIntExtra("FILM_ID", 0)
        if (filmId != 0){
            viewModel.fetchMovieDetails(filmId)
    }

        setupObservers()
        setupBackButton()

    }
    private fun setupObservers(){
        viewModel.movieDetails.observe(this) { movie ->
            binding.textTitleFilm.text = movie.title
            binding.textYear.text = movie.releaseDate
            binding.textTime.text = "${movie.runtime} min"
            binding.textGener.text = movie.genres.joinToString {it.name} // Converte a lista de gêneros em uma string


            Glide.with(this)
                .load(movie.imageBackdropUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(binding.imagemCapaDetails)

            Glide.with(this)
                .load(movie.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(binding.imagemBackground)

            val pagerAdapter = DetailsPagerAdapter(this, movie)
            binding.viewPager2Detail.adapter = pagerAdapter // Configurar o ViewPager2 com o adaptador

            // Configurar o TabLayout com o ViewPager2
            TabLayoutMediator(binding.tabLayoutDetail, binding.viewPager2Detail) { tab, position ->
                tab.text = when (position) {
                    0 -> "About"
                    1 -> "Review"
                    2 -> "Cast"
                    else -> ""
                }
            }.attach()
        }
    }

    private fun setupBackButton() {
        binding.icBackDetails.setOnClickListener {
            val back = Intent(this, SearchListActivity::class.java)
            startActivity(back)
        }
    }
}