package br.com.gallodev.filmix.ui.presentation.listFlims

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.databinding.ActivityListFilmsBinding
import br.com.gallodev.filmix.ui.data.api.Category
import br.com.gallodev.filmix.ui.presentation.DetailsFilm.DetailsFilmActivity
import br.com.gallodev.filmix.ui.presentation.searchFilms.SearchListActivity

class ListFilmsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListFilmsBinding
    private lateinit var viewModel: ListFilmViewModel

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

        // Configuração de apresentação da tela de inicial
        viewModel.buscaFilmePorCategoria(Category.ASSISTIDOS)

        configClickCategory()

    }

    override fun onResume() {
        super.onResume()
        searchViewModel()
        recyclerViewHorizontal()
        recyclerViewColumn()
        configGoHome()
        configGoSearch()
        configGoList()
        //recyclerViewSearch()
    }

    private fun configClickCategory() {
        val assistidos = findViewById<TextView>(R.id.now_playing)
        val lancamoentos = findViewById<TextView>(R.id.update)
        val melhores = findViewById<TextView>(R.id.the_bests)
        val populares = findViewById<TextView>(R.id.popular)

        if (assistidos == null || lancamoentos == null || melhores == null || populares == null) {
            return
        }

        assistidos.setOnClickListener {
            viewModel.buscaFilmePorCategoria(Category.ASSISTIDOS)
        }
        lancamoentos.setOnClickListener {
            viewModel.buscaFilmePorCategoria(Category.LANCAMENTO)
        }
        melhores.setOnClickListener {
            viewModel.buscaFilmePorCategoria(Category.MELHORES_AVALIADOS)
        }
        populares.setOnClickListener {
            viewModel.buscaFilmePorCategoria(Category.POPULARES)
        }

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

    private fun recyclerViewColumn() {

        val recyclerViewColuna = findViewById<RecyclerView>(R.id.recyclerView_coluna)
        recyclerViewColuna.adapter = adapterColumn // Configurando o adapter
        val layoutManager =
            GridLayoutManager(this, 3) // Configurando o GridLayoutManager corretamente
        recyclerViewColuna.layoutManager = layoutManager
        //recyclerViewColuna.isNestedScrollingEnabled = true // Habilitando o deslocamento aninhado

    }
}