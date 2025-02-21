package br.com.gallodev.filmix

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.gallodev.filmix.databinding.ActivitySphashBinding

class ActivitySphash : AppCompatActivity() {

    private lateinit var binding: ActivitySphashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySphashBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.animacaoTelaSplash.postDelayed({
            val animacao = Intent(this, MainActivity::class.java)
        startActivity(animacao)
            finish()
        },4000)
    }
}