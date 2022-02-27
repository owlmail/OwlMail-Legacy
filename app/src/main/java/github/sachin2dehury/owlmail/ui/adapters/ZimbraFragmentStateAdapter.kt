package github.sachin2dehury.owlmail.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import github.sachin2dehury.owlmail.data.constants.ZimbraFolder
import github.sachin2dehury.owlmail.ui.fragment.HomeFragment
import github.sachin2dehury.owlmail.ui.fragment.SearchTabFragment

class ZimbraFragmentStateAdapter(homeFragment: HomeFragment) : FragmentStateAdapter(homeFragment) {

    private val fragments = mutableListOf<Fragment>()

    val tabName = listOf(
        ZimbraFolder.INBOX,
        ZimbraFolder.SENT,
        ZimbraFolder.DRAFT,
        ZimbraFolder.JUNK,
        ZimbraFolder.TRASH
    )

    fun initFragments() {
        if (fragments.isEmpty()) {
            tabName.forEach { fragments.add(SearchTabFragment(it)) }
        }
    }

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}
