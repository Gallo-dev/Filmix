package br.com.gallodev.filmix.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.databinding.ActivityListaFilmesBinding
import br.com.gallodev.filmix.ui.model.Filme
import br.com.gallodev.filmix.ui.recyclerview.adapter.ListaDezMelhoresAdapter

class ListaFilmesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaFilmesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFilmesBinding.inflate(layoutInflater)
      //enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_horizontal)

        val filmes = listOf(
            Filme("https://preview.redd.it/filmes-medievais-v0-xv51jh5nrc6d1.jpg?width=322&format=pjpg&auto=webp&s=0ca95e63d9c3e36413ec9899de9e22dd59dd1849"),
            Filme("https://johto.legiaodosherois.com.br/wp-content/uploads/2024/07/legiao_rJ_Oo1uqZ90G.png"),
            Filme("https://br.web.img3.acsta.net/pictures/210/542/21054207_20131030221640204.jpg"),
            Filme("https://www.cinehorror.com.br/imagens-posts/it-capitulo-2_04-09-19_11-04-05_max.jpg"),
            Filme("https://m.media-amazon.com/images/I/61ytvLnFNkL._AC_UF894,1000_QL80_.jpg"),
            Filme("https://midias.imagemfilmes.com.br/capas/97d33119-b36d-459f-b042-9a9730ec4fa0_m.jpg?2024-08-09T14:12:15.175350"),
            Filme("https://br.web.img3.acsta.net/c_310_420/img/7b/2a/7b2aee713aa190fa761e90559c92255e.PNG"),
            Filme("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrx6Uuap457t0CEerOtP8WexGIvg1Pq5Vdlg&s"),
            Filme("https://i.pinimg.com/474x/ed/7b/65/ed7b653111773824d5b6d6177327686c.jpg"),
            Filme("https://midias.imagemfilmes.com.br/capas/9bb5d2ea-6328-46d5-82d9-a0b4da2bb2b3_m.jpg?2021-10-25T17:04:23.611260")

        )
        val adapter = ListaDezMelhoresAdapter(filmes)
        recyclerView.adapter = adapter







    }
}