package br.com.gallodev.filmix.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.ui.model.Filme
import com.bumptech.glide.Glide

class ListaColunaAdapter(

    private val filmesEmColuna: List<Filme>

) : RecyclerView.Adapter<ListaColunaAdapter.FilmesEmColunaViewHolder>() {

    class FilmesEmColunaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filmesEmColunaImageView: ImageView = itemView.findViewById(R.id.imagem_item_filme)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesEmColunaViewHolder {
        val filmeView = LayoutInflater.from(parent.context).inflate(R.layout.filme_item, parent, false)
        return FilmesEmColunaViewHolder(filmeView)
    }

    override fun onBindViewHolder(holder: ListaColunaAdapter.FilmesEmColunaViewHolder, position: Int) {
        val currentMovie = filmesEmColuna[position]
        Glide.with(holder.itemView.context)
            .load(currentMovie.imagemUrl)
            .into(holder.filmesEmColunaImageView)
    }

    override fun getItemCount(): Int = filmesEmColuna.size

}





