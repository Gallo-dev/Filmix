package br.com.gallodev.filmix.ui.presentation.DetailsFilm

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse

class DetailsPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val movie: MovieDetailResponse
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        AboutFragment(),
        ReviewFragment(),
        CastFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AboutFragment.newInstance(movie.overview)
            1 -> ReviewFragment.newInstance(movie.reviews?.reviews?: emptyList()) // Passe a lista de reviews
            2 -> CastFragment.newInstance(movie.credits?.cast?: emptyList()) // Passe a lista de cast
            else -> AboutFragment.newInstance(movie.overview)
        }
    }
}