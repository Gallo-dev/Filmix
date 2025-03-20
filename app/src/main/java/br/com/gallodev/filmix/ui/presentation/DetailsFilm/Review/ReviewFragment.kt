package br.com.gallodev.filmix.ui.presentation.DetailsFilm.Review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gallodev.filmix.databinding.FragmentReviewBinding
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse
import java.util.ArrayList

@Suppress("DEPRECATION")
class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var reviewsAdapter: ReviewsAdapter

    companion object {
        private const val ARG_REVIEWS = "reviews"

        // Método estático para criar uma instância do fragmento
        fun newInstance(reviews: List<MovieDetailResponse.Review>): ReviewFragment {
            val fragment = ReviewFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_REVIEWS, ArrayList(reviews))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reviewsList = arguments?.getParcelableArrayList<MovieDetailResponse.Review>(ARG_REVIEWS)
            ?: emptyList()
        reviewsAdapter = ReviewsAdapter(reviewsList)
        binding.reviewRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reviewsAdapter
        }
        reviewsAdapter.updateReview(reviewsList) // Atualize o adaptador com a lista de reviews
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}