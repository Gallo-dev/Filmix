package br.com.gallodev.filmix.ui.presentation.DetailsFilm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.databinding.ItemCastBinding
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse
import com.bumptech.glide.Glide

class CastAdapter(private var castList: List<MovieDetailResponse.CastMember>) :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

        class CastViewHolder(val binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val cast = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(cast)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val castManber = castList[position]

        val imageUrl = if (castManber.profilePath.isNullOrEmpty()) {
            "https://via.placeholder.com/150" // Se não houver imagem, usa um placeholder
        }else{
            "https://image.tmdb.org/t/p/w500/${castManber.profilePath}"
        }

        holder.binding.itemCastNameActor.text = castManber.name
        holder.binding.itemCastCharacter.text = castManber.character

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)// Se a imagem não estiver disponível, usa um placeholder
            .error(android.R.drawable.ic_delete)// Se houver um erro ao carregar a imagem
            .into(holder.binding.imageCastActor) // Carrega a imagem no ImageView
    }
    override fun getItemCount() = castList.size

    fun updatecast(newCast: List<MovieDetailResponse.CastMember>){
        castList = newCast
        notifyDataSetChanged()
    }
}