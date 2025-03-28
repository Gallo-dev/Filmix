package br.com.gallodev.filmix.ui.presentation.listFlims.topRated

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.databinding.FragmentNowPlayingBinding
import br.com.gallodev.filmix.ui.data.api.Category
import br.com.gallodev.filmix.ui.data.api.Film
import br.com.gallodev.filmix.ui.presentation.listFlims.ListColumnAdapter
import br.com.gallodev.filmix.ui.presentation.listFlims.ListFilmViewModel

@Suppress("DEPRECATION")
class TopRatedFragment : Fragment() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!

    private lateinit var listColumnAdapter: ListColumnAdapter
    private lateinit var viewModel: ListFilmViewModel
//    private lateinit var nowPlayingAdapter: NowPlayingAdapter

    companion object {
        private const val ARG_FILMS_TOP_RATED = "films"

        // Método estático para criar uma instância do fragmento
        fun newInstance(films: List<Film>): TopRatedFragment {
            val fragment = TopRatedFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_FILMS_TOP_RATED, ArrayList(films))
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

        viewModel.buscaFilmePorCategoria(Category.TOP_RATED)

    }

    private fun setupRecyclerView() {
        listColumnAdapter = ListColumnAdapter(emptyList()) // Inicializa o adaptador aqui
        binding.recyclerViewNowPlaying.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = listColumnAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.buscaFilmePorCategoria(Category.TOP_RATED)
        viewModel.films.observe(viewLifecycleOwner) { films ->
            listColumnAdapter.update(films)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}