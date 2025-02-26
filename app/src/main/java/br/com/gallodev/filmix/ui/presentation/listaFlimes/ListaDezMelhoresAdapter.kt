package br.com.gallodev.filmix.ui.presentation.listaFlimes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.ui.data.api.Film
import br.com.gallodev.filmix.ui.data.model.Filme
import com.bumptech.glide.Glide

class ListaDezMelhoresAdapter(

    private var dezMelhores: List<Film>

) : RecyclerView.Adapter<ListaDezMelhoresAdapter.FilmesViewHolder>() {

    class FilmesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dezMelhoresImageView: ImageView = itemView.findViewById(R.id.imagem_item_filme)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        val filmeView =
            LayoutInflater.from(parent.context).inflate(R.layout.filme_item, parent, false)
        return FilmesViewHolder(filmeView)
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        val currentMovie = dezMelhores[position]
        Glide.with(holder.itemView.context)
            .load(currentMovie.imageUrl)
            .into(holder.dezMelhoresImageView)

    }

    override fun getItemCount(): Int = dezMelhores.size

    fun update(filmes: List<Film>){
        Log.i("ListaDezMelhoresAdapter", "Filmes encontrados: ${filmes.size}")
        this.dezMelhores = filmes
        notifyDataSetChanged()
    }
}


