package br.com.gallodev.filmix.ui.presentation.DetailsFilm.Review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.databinding.ItemReviewsBinding
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse
import com.bumptech.glide.Glide

class ReviewsAdapter(private var reviewsList: List<MovieDetailResponse.Review>) :
    RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>(){

        class ReviewsViewHolder(val binding: ItemReviewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val review = ItemReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewsViewHolder(review)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val review = reviewsList[position]

        val imageAvatarUrl = if (review.avatarPath.isNullOrEmpty()) {
            "https://via.placeholder.com/150" // Se não houver imagem, usa um placeholder
        }else{
            "https://image.tmdb.org/t/p/w500/${review.avatarPath}"
        }
        holder.binding.textAuthorReviews.text = review.author
        holder.binding.textContentReviews.text = review.content

        Glide.with(holder.itemView.context)
            .load(imageAvatarUrl)
            .placeholder(R.drawable.perfil_oculto)
            .into(holder.binding.avatarPath)
    }

    override fun getItemCount() = reviewsList.size

    fun updateReview(newReview: List<MovieDetailResponse.Review>){
        reviewsList = newReview
        notifyDataSetChanged()
    }
}
