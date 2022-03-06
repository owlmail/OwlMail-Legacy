package github.sachin2dehury.owlmail.ui.adapters

import androidx.viewpager2.adapter.FragmentStateAdapter
import github.sachin2dehury.owlmail.data.enums.ZimbraFolder
import github.sachin2dehury.owlmail.ui.fragment.HomeFragment
import github.sachin2dehury.owlmail.ui.fragment.SearchTabFragment

class ZimbraFragmentStateAdapter(val tabList: List<ZimbraFolder>, homeFragment: HomeFragment) :
    FragmentStateAdapter(homeFragment) {

    override fun getItemCount() = tabList.size

    override fun createFragment(position: Int) = SearchTabFragment.newInstance(tabList[position])
}
