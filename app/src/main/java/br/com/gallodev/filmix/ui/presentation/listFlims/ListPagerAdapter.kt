package br.com.gallodev.filmix.ui.presentation.listFlims

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.gallodev.filmix.ui.data.api.Film
import br.com.gallodev.filmix.ui.data.api.ResponseFilm
import br.com.gallodev.filmix.ui.presentation.DetailsFilm.About.AboutFragment
import br.com.gallodev.filmix.ui.presentation.DetailsFilm.Cast.CastFragment
import br.com.gallodev.filmix.ui.presentation.DetailsFilm.Review.ReviewFragment
import br.com.gallodev.filmix.ui.presentation.listFlims.nowPlaying.NowPlayingFragment
import br.com.gallodev.filmix.ui.presentation.listFlims.popular.PopularFragment
import br.com.gallodev.filmix.ui.presentation.listFlims.topRated.TopRatedFragment
import br.com.gallodev.filmix.ui.presentation.listFlims.upComing.UpComingFragment

class ListPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val film: List<Film>
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        NowPlayingFragment(),
        UpComingFragment(),
        TopRatedFragment(),
        PopularFragment()
    )

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment {
            return when (position) {

                0 -> NowPlayingFragment.newInstance(film)
                1 -> UpComingFragment.newInstance(film)
                2 -> TopRatedFragment.newInstance(film)
                3 -> PopularFragment.newInstance(film)
                else -> NowPlayingFragment.newInstance(film)
            }
        }
}