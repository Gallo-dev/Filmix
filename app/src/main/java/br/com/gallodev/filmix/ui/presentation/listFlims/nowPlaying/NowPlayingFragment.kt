package br.com.gallodev.filmix.ui.presentation.listFlims.nowPlaying

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gallodev.filmix.databinding.FragmentNowPlayingBinding
import br.com.gallodev.filmix.ui.data.api.Category
import br.com.gallodev.filmix.ui.data.api.Film
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse
import br.com.gallodev.filmix.ui.presentation.DetailsFilm.Cast.CastFragment
import br.com.gallodev.filmix.ui.presentation.listFlims.ListColumnAdapter
import br.com.gallodev.filmix.ui.presentation.listFlims.ListFilmViewModel


@Suppress("DEPRECATION")
class NowPlayingFragment : Fragment() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!

    private lateinit var listColumnAdapter: ListColumnAdapter
    private lateinit var viewModel: ListFilmViewModel
//    private lateinit var nowPlayingAdapter: NowPlayingAdapter

    companion object {
        private const val ARG_FILMS = "films"
        // Método estático para criar uma instância do fragmento
        fun newInstance(films: List<Film>): NowPlayingFragment {
            val fragment = NowPlayingFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_FILMS, ArrayList(films))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("NowPlayingFragment", "onViewCreated called")
        viewModel = ViewModelProvider(this)[ListFilmViewModel::class.java]

        setupRecyclerView()
        observeViewModel()

        viewModel.buscaFilmePorCategoria(Category.NOW_PLAYING)

    }

    private fun setupRecyclerView() {
        listColumnAdapter = ListColumnAdapter(emptyList()) // Inicializa o adaptador aqui
        binding.recyclerViewNowPlaying.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = listColumnAdapter
        }
    }

    private fun observeViewModel(){
        viewModel.buscaFilmePorCategoria(Category.NOW_PLAYING)
        viewModel.films.observe(viewLifecycleOwner){films ->
            listColumnAdapter.update(films)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



