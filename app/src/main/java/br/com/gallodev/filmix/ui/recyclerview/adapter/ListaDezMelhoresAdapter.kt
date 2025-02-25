package br.com.gallodev.filmix.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.ui.model.Filme
import com.bumptech.glide.Glide

class ListaDezMelhoresAdapter(

    private val filmes: List<Filme>

) : RecyclerView.Adapter<ListaDezMelhoresAdapter.FilmesViewHolder>() {

    class FilmesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filmesImageView: ImageView = itemView.findViewById(R.id.imagem_item_filme)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        val filmeView =
            LayoutInflater.from(parent.context).inflate(R.layout.filme_item, parent, false)
        return FilmesViewHolder(filmeView)
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        val currentMovie = filmes[position]
        Glide.with(holder.itemView.context)
            .load(currentMovie.imagemUrl)
            .into(holder.filmesImageView)

    }

    override fun getItemCount(): Int = filmes.size
}


