package br.com.gallodev.filmix.ui.presentation.searchFilms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.ui.data.api.Film
import com.bumptech.glide.Glide

class SearchListAdapter(

    private var searchListFilm: List<Film>,
    private  val onItemClick: (Film) -> Unit


) : RecyclerView.Adapter<SearchListAdapter.SearchListViewHolder>() {

    class SearchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val searchListImageView: ImageView = itemView.findViewById(R.id.imagem_item_filme)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val filmeView = LayoutInflater.from(parent.context).inflate(R.layout.filme_item, parent, false)
        return SearchListViewHolder(filmeView)
    }

    override fun getItemCount(): Int = searchListFilm.size

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        val currentMovie = searchListFilm[position]
        Glide.with(holder.itemView.context)
            .load(currentMovie.imageUrl)
            .into(holder.searchListImageView)

        // Configuração do clique no item
        holder.itemView.setOnClickListener {
            onItemClick(currentMovie)
        }
    }
    fun updateList(newList: List<Film>){
        searchListFilm = newList
        notifyDataSetChanged()
    }
}


