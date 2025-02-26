package br.com.gallodev.filmix.ui.presentation.listaFlimes

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.databinding.ActivityListaFilmesBinding
import br.com.gallodev.filmix.ui.data.api.Film

class ListaFilmesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaFilmesBinding
    private lateinit var viewModel: ListaFilmViewModel
    private val adapterColuna by lazy {
        ListaColunaAdapter(emptyList())
    }
    private val adapterHorizontal by lazy {
        ListaDezMelhoresAdapter(emptyList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFilmesBinding.inflate(layoutInflater)
        //enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ListaFilmViewModel::class.java]

        searchViewModel()
        recyclerViewHorizontal()
        recyclerViewColumn()
        viewModel.buscaFilm()
    }

    private fun searchViewModel() {
        viewModel.films.observe(this) { filmes ->
            adapterColuna.update(filmes)

        }
        viewModel.tenBestFilms.observe(this){ filmes ->
            adapterHorizontal.update(filmes)
        }
    }

    private fun recyclerViewHorizontal() {
        val recyclerViewHorizontal = findViewById<RecyclerView>(R.id.recyclerView_horizontal)
        recyclerViewHorizontal.adapter = adapterHorizontal // Configurando o adapter
        recyclerViewHorizontal.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // Configurando o layout manager
    }

    private fun recyclerViewColumn() {

        val recyclerViewColuna = findViewById<RecyclerView>(R.id.recyclerView_coluna)
        recyclerViewColuna.adapter = adapterColuna // Configurando o adapter
        val layoutManager = GridLayoutManager(this, 3) // Configurando o GridLayoutManager corretamente
        recyclerViewColuna.layoutManager = layoutManager
        recyclerViewColuna.isNestedScrollingEnabled = true // Habilitando o deslocamento aninhado

    }
}