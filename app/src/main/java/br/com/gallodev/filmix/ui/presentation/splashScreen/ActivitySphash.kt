package br.com.gallodev.filmix.ui.presentation.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.gallodev.filmix.databinding.ActivitySphashBinding
import br.com.gallodev.filmix.ui.presentation.listaFlimes.ListaFilmesActivity

class ActivitySphash : AppCompatActivity() {

    private lateinit var binding: ActivitySphashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySphashBinding.inflate(layoutInflater)
        //enableEdgeToEdge()
        supportActionBar?.hide()

        setContentView(binding.root)
        setupSplash()
    }
    private fun setupSplash() {
        binding.animacaoTelaSplash.postDelayed({
            val animacao = Intent(this, ListaFilmesActivity::class.java)
            startActivity(animacao)
            finish()
        }, 4000)
    }
}