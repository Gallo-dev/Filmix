package br.com.gallodev.filmix.ui.presentation.listFlims

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.ui.data.api.Film
import com.bumptech.glide.Glide

class ListColumnAdapter(

    private var filmesEmColuna: List<Film>

) : RecyclerView.Adapter<ListColumnAdapter.FilmesEmColunaViewHolder>() {

    class FilmesEmColunaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filmesEmColunaImageView: ImageView = itemView.findViewById(R.id.imagem_item_filme)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesEmColunaViewHolder {
        val filmeView = LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return FilmesEmColunaViewHolder(filmeView)
    }

    override fun onBindViewHolder(holder: FilmesEmColunaViewHolder, position: Int) {
        val currentMovie = filmesEmColuna[position]
        Glide.with(holder.itemView.context)
            .load(currentMovie.imageUrl)
            .into(holder.filmesEmColunaImageView)
    }

    override fun getItemCount(): Int = filmesEmColuna.size

    fun update(filmes: List<Film>){
        Log.i("ListaColunaAdapter", "Filmes encontrados: ${filmes.size}")
        this.filmesEmColuna = filmes
        notifyDataSetChanged()
    }

}





