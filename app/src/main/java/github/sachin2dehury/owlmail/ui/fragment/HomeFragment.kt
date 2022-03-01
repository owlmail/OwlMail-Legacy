package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.FragmentHomeBinding
import github.sachin2dehury.owlmail.ui.adapters.ZimbraFragmentStateAdapter

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null

    private val args: HomeFragmentArgs by navArgs()

    private val zimbraFragmentStateAdapter get() = ZimbraFragmentStateAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setUpTabLayout()
    }

    private fun setUpTabLayout() = _binding?.run {
        zimbraFragmentStateAdapter.initFragments()
        viewPager.adapter = zimbraFragmentStateAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = zimbraFragmentStateAdapter.tabName[position].value.uppercase()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.search_menu, menu)
//        val searchView = menu.findItem(R.id.searchBar).actionView as SearchView
//        searchView.queryHint = getString(R.string.search)
//        searchView.isSubmitButtonEnabled = true
//        searchView.setOnCloseListener {
//            false
//        }
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                return true
//            }
//
//            override fun onQueryTextChange(query: String) = false
//        })
//        super.onCreateOptionsMenu(menu, inflater)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
