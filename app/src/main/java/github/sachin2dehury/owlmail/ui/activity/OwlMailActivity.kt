package github.sachin2dehury.owlmail.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.databinding.ActivityOwlMailBinding

@AndroidEntryPoint
class OwlMailActivity : AppCompatActivity() {

    private var binding: ActivityOwlMailBinding? = null

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOwlMailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.root?.doOnLayout {
            navController = it.findNavController()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navController = null
        binding = null
    }
}
