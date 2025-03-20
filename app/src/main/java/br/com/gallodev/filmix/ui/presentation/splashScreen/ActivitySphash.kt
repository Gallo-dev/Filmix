package br.com.gallodev.filmix.ui.presentation.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.gallodev.filmix.databinding.ActivitySphashBinding
import br.com.gallodev.filmix.ui.presentation.listFlims.ListFilmsActivity

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
            val animacao = Intent(this, ListFilmsActivity::class.java)
            startActivity(animacao)
            finish()
        }, 4000)
    }
}