package br.com.gallodev.filmix.ui.presentation.DetailsFilm.Cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.gallodev.filmix.databinding.FragmentCastBinding
import br.com.gallodev.filmix.ui.data.api.MovieDetailResponse

@Suppress("DEPRECATION")
class CastFragment : Fragment() {

    private var _binding: FragmentCastBinding? = null
    private val binding get() = _binding!!

    private lateinit var castAdapter: CastAdapter

    companion object {
        private const val ARG_CAST = "cast"

        // Método estático para criar uma instância do fragmento
        fun newInstance(cast: List<MovieDetailResponse.CastMember>): CastFragment {
            val fragment = CastFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_CAST, ArrayList(cast))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {  // Infla o layout do fragmento
        _binding = FragmentCastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val castList = arguments?.getParcelableArrayList<MovieDetailResponse.CastMember>(ARG_CAST)
            ?: emptyList() // Obtém a lista de cast

        // Configura o RecyclerView do CastFragment
        castAdapter = CastAdapter(castList)
        binding.castRecyclerView.apply { layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = castAdapter
        }
        // Atualize o adaptador com a lista de cast
        castAdapter.updatecast(castList)
    }

    override fun onDestroyView() { // Limpar a referência ao binding quando o fragmento é destruído
        super.onDestroyView()
        _binding = null
    }
}