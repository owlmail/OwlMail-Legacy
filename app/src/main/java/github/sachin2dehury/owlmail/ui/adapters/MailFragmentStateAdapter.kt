package github.sachin2dehury.owlmail.ui.adapters

import androidx.viewpager2.adapter.FragmentStateAdapter
import github.sachin2dehury.owlmail.ui.activity.MailActivity
import github.sachin2dehury.owlmail.ui.fragment.MailFragment

class MailFragmentStateAdapter(private val activity: MailActivity) :
    FragmentStateAdapter(activity) {

    private val fragmentCount = 5

    override fun getItemCount() = fragmentCount

    override fun createFragment(position: Int) = MailFragment(activity.getTabName(position))
}