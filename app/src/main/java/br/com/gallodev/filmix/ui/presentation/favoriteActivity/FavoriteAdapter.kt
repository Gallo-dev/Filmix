package br.com.gallodev.filmix.ui.presentation.favoriteActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse
import br.com.gallodev.filmix.ui.data.model.FavoriteFilm
import com.bumptech.glide.Glide

class FavoriteAdapter(

    private val favoriteList: MutableList<MovieDetailResponse>,
    private val onItemClick: (MovieDetailResponse) -> Unit

) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val favoriteImageView: ImageView = itemView.findViewById(R.id.image_item_search)
        val favoriteTitle: TextView = itemView.findViewById(R.id.text_title_item_search)
        val favoriteYear: TextView = itemView.findViewById(R.id.text_item_search_year)
        val favoriteLike: TextView = itemView.findViewById(R.id.text_item_like_search)
        val favoriteGenre: TextView = itemView.findViewById(R.id.text_item_search_gender)
        val favoriteTime: TextView = itemView.findViewById(R.id.text_item_search_timer)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val favoriteView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_film, parent, false)
        return FavoriteViewHolder(favoriteView)
    }

    override fun getItemCount(): Int = favoriteList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {

        val movie = favoriteList[position]
        holder.favoriteTitle.text = favoriteList[position].title
        holder.favoriteYear.text = favoriteList[position].releaseDate
        holder.favoriteLike.text = favoriteList[position].voteAverage.toString()
        holder.favoriteGenre.text = favoriteList[position].genres?.joinToString { it.name }
        holder.favoriteTime.text = favoriteList[position].runtime.toString() + "min"

        Glide.with(holder.itemView.context)
            .load(favoriteList[position].imageUrl)
            .into(holder.favoriteImageView)

        holder.itemView.setOnClickListener {
            onItemClick(movie)

        }

    }


    fun updateList(favoriteNewList: List<MovieDetailResponse>) {
        favoriteList.clear()
        favoriteList.addAll(favoriteNewList)
        notifyDataSetChanged()

    }

}