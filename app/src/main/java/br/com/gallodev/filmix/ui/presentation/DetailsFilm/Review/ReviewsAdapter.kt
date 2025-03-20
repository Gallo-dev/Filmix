package br.com.gallodev.filmix.ui.presentation.DetailsFilm.Review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gallodev.filmix.databinding.ItemReviewsBinding
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse

class ReviewsAdapter(private var reviewsList: List<MovieDetailResponse.Review>) :
    RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>(){

        class ReviewsViewHolder(val binding: ItemReviewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val review = ItemReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewsViewHolder(review)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val review = reviewsList[position]

        holder.binding.textAuthorReviews.text = review.author
        holder.binding.textContentReviews.text = review.content
    }

    override fun getItemCount() = reviewsList.size

    fun updateReview(newReview: List<MovieDetailResponse.Review>){
        reviewsList = newReview
        notifyDataSetChanged()
    }
}
