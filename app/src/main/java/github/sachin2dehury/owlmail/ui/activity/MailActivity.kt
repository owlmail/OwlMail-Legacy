package github.sachin2dehury.owlmail.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ActivityMailBinding
import github.sachin2dehury.owlmail.ui.adapters.MailFragmentStateAdapter

@AndroidEntryPoint
class MailActivity : AppCompatActivity() {

    private var _binding: ActivityMailBinding? = null
    private val binding get() = _binding!!

    private var mailFragmentStateAdapter: MailFragmentStateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
    }

    private fun setUpUi() {
        mailFragmentStateAdapter = MailFragmentStateAdapter(this)
        binding.viewPager.adapter = mailFragmentStateAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getTabName(position)
        }

        binding.composeButton.setOnClickListener {

        }
    }

    fun getTabName(position: Int) = when (position) {
        0 -> getString(R.string.inbox)
        1 -> getString(R.string.sent)
        2 -> getString(R.string.draft)
        3 -> getString(R.string.junk)
        4 -> getString(R.string.trash)
        else -> getString(R.string.inbox)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}