package br.com.gallodev.filmix.ui.presentation.DetailsFilm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.gallodev.filmix.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding !!

    companion object {
        private const val ARG_OVERVIEW = "overview"

        // Método estático para criar uma instância do fragmento
        fun newInstance(overview: String): AboutFragment {
            val fragment = AboutFragment()
            val args = Bundle()
            args.putString(ARG_OVERVIEW, overview)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {  // Inflar o layout do fragmento
        _binding = FragmentAboutBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) // Configurar a interface do usuário
        val overview = arguments?.getString(ARG_OVERVIEW) ?: "Synopsis unavailable."
        binding.textOverview.text = overview

    }

    override fun onDestroyView() { // Limpar a referência ao binding quando o fragmento é destruído
        super.onDestroyView()
        _binding = null
    }

}