package br.com.gallodev.filmix.ui.presentation.listFlims

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.databinding.ActivityListFilmsBinding
import br.com.gallodev.filmix.ui.data.api.Category
import br.com.gallodev.filmix.ui.data.api.Film
import br.com.gallodev.filmix.ui.presentation.DetailsFilm.DetailsFilmActivity
import br.com.gallodev.filmix.ui.presentation.searchFilms.SearchListActivity
import com.google.android.material.tabs.TabLayoutMediator

class ListFilmsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListFilmsBinding
    private lateinit var viewModel: ListFilmViewModel
    private lateinit var pagerAdapter: ListPagerAdapter

    private val adapterColumn by lazy {
        ListColumnAdapter(emptyList())
    }
    private val adapterHorizontal by lazy {
        ListTenBestAdapter(emptyList())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListFilmsBinding.inflate(layoutInflater)
        //enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(binding.root)

        // Configuração do ViewModel
        viewModel = ViewModelProvider(this)[ListFilmViewModel::class.java]

        viewModel.films.observe(this) { films ->
            setupViewPager(films)
        }

        // Configuração de apresentação da tela de inicial
        viewModel.buscaFilmePorCategoria(Category.NOW_PLAYING)
//        viewModel.buscaFilmePorCategoria(Category.UPCOMING)

    }

    private fun setupViewPager(films: List<Film>) {
        pagerAdapter = ListPagerAdapter(this, films)
        binding.viewPager2Home.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayoutHome, binding.viewPager2Home) { tab, position ->
            when (position) {
                0 -> tab.text = "Now Playing"
                1 -> tab.text = "UpComing"
                2 -> tab.text = "Top Rated"
                3 -> tab.text = "Popular"
                else -> ""
            }
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        searchViewModel()
        recyclerViewHorizontal()
        configGoHome()
        configGoSearch()
        configGoList()
    }


    private fun searchViewModel() {
        viewModel.films.observe(this) { filmes ->
            adapterColumn.update(filmes)

        }
        viewModel.tenBestFilms.observe(this) { filmes ->
            adapterHorizontal.update(filmes)
        }
    }

    private fun configGoHome() {
        binding.icHome.setOnClickListener {
            val intent =
                Intent(this, ListFilmsActivity::class.java) // Navega para a tela de pesquisa
            startActivity(intent)
        }

    }

    private fun configGoSearch() {
        binding.icSearch.setOnClickListener {
            val intent =
                Intent(this, SearchListActivity::class.java) // Navega para a tela de pesquisa
            startActivity(intent)
        }
    }

    private fun configGoList() {
        binding.icBookMarkListBorder.setOnClickListener {
            val intent =
                Intent(this, DetailsFilmActivity::class.java) // Navega para a tela de pesquisa
            startActivity(intent)
        }
    }

    private fun recyclerViewHorizontal() {
        val recyclerViewHorizontal = findViewById<RecyclerView>(R.id.recyclerView_horizontal)
        recyclerViewHorizontal.adapter = adapterHorizontal // Configurando o adapter
        recyclerViewHorizontal.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL, // Configurando o layout manager
                false
            )
    }

}