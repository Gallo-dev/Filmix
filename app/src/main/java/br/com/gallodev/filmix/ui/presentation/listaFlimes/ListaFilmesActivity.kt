package br.com.gallodev.filmix.ui.presentation.listaFlimes

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.databinding.ActivityListaFilmesBinding
import br.com.gallodev.filmix.ui.data.api.Categoria
import br.com.gallodev.filmix.ui.data.api.FilmService
import retrofit2.Retrofit

class ListaFilmesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaFilmesBinding
    private lateinit var viewModel: ListaFilmViewModel

    private val adapterColumn by lazy {
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

        configClickCategory()

        viewModel.buscaFilmePorCategoria(Categoria.ASSISTIDOS)

    }
    override fun onResume() {
        super.onResume()
        searchViewModel()
        recyclerViewHorizontal()
        recyclerViewColumn()
    }

    private fun configClickCategory() {
        Log.i("ListaFilmesActivity", "configClickCategory chamado")
        val assistidos = findViewById<TextView>(R.id.assistidos)
        val lancamoentos = findViewById<TextView>(R.id.lancamento)
        val melhores = findViewById<TextView>(R.id.melhores)
        val populares = findViewById<TextView>(R.id.populares)

        if(assistidos == null || lancamoentos == null || melhores == null || populares == null){
            return
        }

        assistidos.setOnClickListener {
            Log.i("ListaFilmesActivity", "Assistidos clicado")
            viewModel.buscaFilmePorCategoria(Categoria.ASSISTIDOS)
        }
        lancamoentos.setOnClickListener {
            Log.i("ListaFilmesActivity", "Lancamentos clicado")
            viewModel.buscaFilmePorCategoria(Categoria.LANCAMENTO)
        }
        melhores.setOnClickListener {
            Log.i("ListaFilmesActivity", "Melhores clicado")
            viewModel.buscaFilmePorCategoria(Categoria.MELHORES_AVALIADOS)
        }
        populares.setOnClickListener {
            Log.i("ListaFilmesActivity", "Populares clicado")
            viewModel.buscaFilmePorCategoria(Categoria.POPULARES)
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

    private fun recyclerViewHorizontal() {
        val recyclerViewHorizontal = findViewById<RecyclerView>(R.id.recyclerView_horizontal)
        recyclerViewHorizontal.adapter = adapterHorizontal // Configurando o adapter
        recyclerViewHorizontal.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            ) // Configurando o layout manager
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