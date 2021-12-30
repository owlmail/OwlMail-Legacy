package github.sachin2dehury.owlmail.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ActivityMainBinding

@AndroidEntryPoint
class ZimbraActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
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

//    private fun setUpUi() {
//        mailFragmentStateAdapter = MailFragmentStateAdapter(this)
//        binding.viewPager.adapter = mailFragmentStateAdapter
//
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            tab.text = getTabName(position)
//        }
//
//    }

    fun getTabName(position: Int) = when (position) {
        0 -> getString(R.string.inbox)
        1 -> getString(R.string.sent)
        2 -> getString(R.string.draft)
        3 -> getString(R.string.junk)
        4 -> getString(R.string.trash)
        else -> getString(R.string.inbox)
    }

}