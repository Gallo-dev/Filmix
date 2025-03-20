package br.com.gallodev.filmix.ui.presentation.searchFilms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import br.com.gallodev.filmix.databinding.ActivityListSearchBinding
import br.com.gallodev.filmix.ui.presentation.DetailsFilm.DetailsFilmActivity
import br.com.gallodev.filmix.ui.presentation.listFlims.ListFilmsActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListSearchBinding
    private lateinit var viewModel: SearchViewModel

    private val adapterSearch by lazy {
        SearchListAdapter(emptyList()) { film ->
            val intent = Intent(this, DetailsFilmActivity::class.java)
            intent.putExtra("FILM", film) // Passa o objeto Film como um extra
            intent.putExtra("FILM_ID", film.id) // Passa o ID do filme como um extra
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSearchBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        //enableEdgeToEdge();
        setContentView(binding.root)

        // Configuração do ViewModel
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        configBack()
        configSearchView()
        recyclerViewSearch()
        configBackHome()

        viewModel.films.observe(this) { films ->
            adapterSearch.updateList(films)
        }
    }

    private fun configBack() {
        binding.icBack.setOnClickListener {
            val back = Intent(this, ListFilmsActivity::class.java)
            startActivity(back)
            finish()
        }
    }

    private fun configBackHome() {
        binding.icHome.setOnClickListener {
            val home = Intent(this, ListFilmsActivity::class.java)
            startActivity(home)
            finish()
        }
    }


    private fun configSearchView() {

        var searchJob: Job? = null

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { // função para quando o texto for submetido
                Log.i("TAG", "onQueryTextSubmit: $query")
                query?.let {
                    viewModel.searchFilm(it)
                }
                return true // Retorne true para indicar que o evento foi consumido
            }

            override fun onQueryTextChange(newText: String?): Boolean { // função para quando o texto for alterado
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    delay(500)
                    newText?.let { text ->
                        if (text.isNotEmpty()) {
                            viewModel.searchFilm(text)
                        }
                    }
                }
                return true // Retorne true para indicar que o evento foi consumido
            }
        })
    }

    private fun recyclerViewSearch() {
        binding.recyclerViewSearchFilm.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerViewSearchFilm.adapter = adapterSearch
    }
}