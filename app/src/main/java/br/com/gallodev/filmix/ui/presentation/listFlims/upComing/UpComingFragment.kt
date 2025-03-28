package br.com.gallodev.filmix.ui.presentation.listFlims.upComing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.databinding.FragmentUpComingBinding
import br.com.gallodev.filmix.ui.data.api.Category
import br.com.gallodev.filmix.ui.data.api.Film
import br.com.gallodev.filmix.ui.presentation.listFlims.ListColumnAdapter
import br.com.gallodev.filmix.ui.presentation.listFlims.ListFilmViewModel
import br.com.gallodev.filmix.ui.presentation.listFlims.nowPlaying.NowPlayingFragment
import br.com.gallodev.filmix.ui.presentation.listFlims.nowPlaying.NowPlayingFragment.Companion

@Suppress("DEPRECATION")
class UpComingFragment : Fragment() {

    private var _binding: FragmentUpComingBinding? = null
    private val binding get() = _binding!!

    private lateinit var listColumnAdapter: ListColumnAdapter
    private lateinit var viewModel: ListFilmViewModel

    companion object {
        private const val ARG_FILMS_UPCOMING = "films"

        // Método estático para criar uma instância do fragmento
        fun newInstance(films: List<Film>): UpComingFragment {
            val fragment = UpComingFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_FILMS_UPCOMING, ArrayList(films))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpComingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("NowPlayingFragment", "onViewCreated called")
        viewModel = ViewModelProvider(this)[ListFilmViewModel::class.java]

        setupRecyclerView()
        observeViewModel()

        viewModel.buscaFilmePorCategoria(Category.UPCOMING)
    }

    private fun setupRecyclerView() {
        listColumnAdapter = ListColumnAdapter(emptyList()) // Inicializa o adaptador aqui
        binding.recyclerViewUpComing.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = listColumnAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.buscaFilmePorCategoria(Category.UPCOMING)
        viewModel.films.observe(viewLifecycleOwner) { films ->
            listColumnAdapter.update(films)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}