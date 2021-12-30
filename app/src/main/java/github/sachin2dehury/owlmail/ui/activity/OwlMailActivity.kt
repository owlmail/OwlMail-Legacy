package github.sachin2dehury.owlmail.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.databinding.ActivityOwlMailBinding

@AndroidEntryPoint
class OwlMailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOwlMailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}