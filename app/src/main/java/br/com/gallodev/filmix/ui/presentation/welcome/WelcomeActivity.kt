package br.com.gallodev.filmix.ui.presentation.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.gallodev.filmix.R
import br.com.gallodev.filmix.databinding.ActivityWelcomeBinding
import br.com.gallodev.filmix.ui.presentation.listFlims.ListFilmsActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(binding.root)

        setupArrow()
    }
    private fun setupArrow() {
        binding.icArrowGo.setOnClickListener {
            val arrow = Intent(this, ListFilmsActivity::class.java)
            startActivity(arrow)
            finish()
        }
    }
}