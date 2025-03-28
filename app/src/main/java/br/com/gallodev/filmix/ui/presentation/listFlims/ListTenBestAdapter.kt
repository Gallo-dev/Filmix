package br.com.gallodev.filmix.ui.presentation.listFlims

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.ui.data.api.Film
import com.bumptech.glide.Glide

class ListTenBestAdapter(

    private var dezMelhores: List<Film>

) : RecyclerView.Adapter<ListTenBestAdapter.FilmesViewHolder>() {

    class FilmesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dezMelhoresImageView: ImageView = itemView.findViewById(R.id.imagem_item_filme)
        val dezMelhoresTextView: TextView = itemView.findViewById(R.id.text_title_item_film)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        val filmeView =
            LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return FilmesViewHolder(filmeView)
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        val currentMovie = dezMelhores[position]

        holder.dezMelhoresTextView.text = currentMovie.title
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


