package br.com.gallodev.filmix.ui.presentation.searchFilms

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.ui.data.api.Film
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse
import com.bumptech.glide.Glide

class SearchListAdapter(

    private var searchListFilm: List<Film>,
    private val onItemClick: (Film) -> Unit


) : RecyclerView.Adapter<SearchListAdapter.SearchListViewHolder>() {

    class SearchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val searchListImageView: ImageView = itemView.findViewById(R.id.image_item_search)
        val searchListTitle: TextView = itemView.findViewById(R.id.text_title_item_search)
        val searchListYear: TextView = itemView.findViewById(R.id.text_item_search_year)
        val searchListGener: TextView = itemView.findViewById(R.id.text_item_search_gender)
        val searchListTime: TextView = itemView.findViewById(R.id.text_item_search_timer)
        val searchListLike: TextView = itemView.findViewById(R.id.text_item_like_search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val filmeView = LayoutInflater.from(parent.context).inflate(R.layout.item_search_film, parent, false)
        return SearchListViewHolder(filmeView)
    }

    override fun getItemCount(): Int = searchListFilm.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        val currentMovie = searchListFilm[position]

        holder.searchListTitle.text = currentMovie.title
        holder.searchListYear.text = currentMovie.releaseDate
        holder.searchListGener.text = currentMovie.genres?.joinToString {it.name}
        holder.searchListTime.text = "${currentMovie.runtime} min"
        holder.searchListLike.text = "${currentMovie.formattedVote}"

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


