package br.com.gallodev.filmix.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.databinding.ActivityListaFilmesBinding
import br.com.gallodev.filmix.ui.model.Filme
import br.com.gallodev.filmix.ui.recyclerview.adapter.ListaColunaAdapter
import br.com.gallodev.filmix.ui.recyclerview.adapter.ListaDezMelhoresAdapter

class ListaFilmesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaFilmesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFilmesBinding.inflate(layoutInflater)
        //enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(binding.root)


        configuraRecyclerViewHorizontal()
        configuraRecyclerViewColuna()
    }

    private fun configuraRecyclerViewHorizontal() {
        val recyclerViewHorizontal = findViewById<RecyclerView>(R.id.recyclerView_horizontal)
        val dezmelhores = listOf(
            Filme("https://midias.imagemfilmes.com.br/capas/680ac54e-014b-4ac7-ad05-db4cc955ebee_m.jpg?2024-10-08T15:57:57.182879"),
            Filme("https://midias.imagemfilmes.com.br/capas/8a825d27-d8b3-4bdc-bf9a-eab76be0b1f7_m.jpg?2024-03-20T15:50:38.153964"),
            Filme("https://midias.imagemfilmes.com.br/capas/fede9d6c-d7e3-41f6-a505-7d2ff4b3a555_m.jpg?2024-03-14T09:29:50.629231"),
            Filme("https://midias.imagemfilmes.com.br/capas/48adcb30-22fd-4f22-ab1c-62f34148c273_m.jpg?2022-12-12T09:54:33.620322"),
            Filme("https://midias.imagemfilmes.com.br/capas/9030fd55-c808-4765-ba1a-30d8970059c5_m.jpg?2025-02-11T18:18:23.649762"),
            Filme("https://midias.imagemfilmes.com.br/capas/91dfba6b-f80e-4083-90c9-6ca88a202768_m.jpg?2025-02-06T17:41:18.183493"),
            Filme("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRy4Cbmhp8278lydXbIWtLzucWIDqEnx1jaGA&s"),
            Filme("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrx6Uuap457t0CEerOtP8WexGIvg1Pq5Vdlg&s"),
            Filme("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQn2CyvbVp7RqdYjCIE80BbR7vE1MlHSmiNnA&s"),
            Filme("https://images-americanas.b2w.io/produtos/2074811914/imagens/ladrao-de-raios-o-capa-do-filme-percy-jackson-rick-riordan/2074811922_1_large.jpg")

        )
        val adapterHorizontal = ListaDezMelhoresAdapter(dezmelhores)
        recyclerViewHorizontal.adapter = adapterHorizontal
    }

    private fun configuraRecyclerViewColuna() {

        val recyclerViewColuna = findViewById<RecyclerView>(R.id.recyclerView_coluna)
        val filmesEmColuna = listOf(
            Filme("https://preview.redd.it/filmes-medievais-v0-xv51jh5nrc6d1.jpg?width=322&format=pjpg&auto=webp&s=0ca95e63d9c3e36413ec9899de9e22dd59dd1849"),
            Filme("https://johto.legiaodosherois.com.br/wp-content/uploads/2024/07/legiao_rJ_Oo1uqZ90G.png"),
            Filme("https://br.web.img3.acsta.net/pictures/210/542/21054207_20131030221640204.jpg"),
            Filme("https://www.cinehorror.com.br/imagens-posts/it-capitulo-2_04-09-19_11-04-05_max.jpg"),
            Filme("https://m.media-amazon.com/images/I/61ytvLnFNkL._AC_UF894,1000_QL80_.jpg"),
            Filme("https://midias.imagemfilmes.com.br/capas/97d33119-b36d-459f-b042-9a9730ec4fa0_m.jpg?2024-08-09T14:12:15.175350"),
            Filme("https://br.web.img3.acsta.net/c_310_420/img/7b/2a/7b2aee713aa190fa761e90559c92255e.PNG"),
            Filme("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrx6Uuap457t0CEerOtP8WexGIvg1Pq5Vdlg&s"),
            Filme("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcVVbFp0diz-dhg8_1AAY9TRx-Q5RQsiSZ5w&s"),
            Filme("https://br.web.img3.acsta.net/pictures/20/01/28/22/11/0608065.jpg"),
            Filme("https://www.showmetech.com.br/wp-content/uploads//2023/12/004-768x1024.webp"),
            Filme("https://jardimdasamericas.com.br/uploads/2024/06/capa-filme-a-semente-do-mal-56037-large.jpg"),
            Filme("https://one-cinema.s3.sa-east-1.amazonaws.com/filmes/joker/10062020/342/capa-joker.jpg"),
            Filme("https://midias.imagemfilmes.com.br/capas/9bb5d2ea-6328-46d5-82d9-a0b4da2bb2b3_m.jpg?2021-10-25T17:04:23.611260")
        )

        val adapterColuna = ListaColunaAdapter(filmesEmColuna)
        recyclerViewColuna.adapter = adapterColuna
        // Configurando o GridLayoutManager corretamente
        val layoutManager = GridLayoutManager(this, 3)
        recyclerViewColuna.layoutManager = layoutManager
        // Garantindo que a rolagem funcione corretamente
        recyclerViewColuna.isNestedScrollingEnabled = true

    }
}