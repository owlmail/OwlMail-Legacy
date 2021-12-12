package github.sachin2dehury.owlmail.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.databinding.ActivityAuthBinding

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private var _binding: ActivityAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setUpNav()
    }

    private fun setUpNav() {
        val navController = binding.navHostFragment.findNavController()
        navController.setGraph(navController.graph, intent.extras)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}