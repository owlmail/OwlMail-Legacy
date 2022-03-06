package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.data.local.Header
import github.sachin2dehury.owlmail.databinding.ItemHeaderViewBinding

@EpoxyModelClass
abstract class HeaderModel : EpoxyModelWithHolder<HeaderModel.Holder>() {

    @EpoxyAttribute
    var header: Header? = null

    private var binding: ItemHeaderViewBinding? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        header?.let { render(it) }
    }

    private fun render(header: Header) = binding?.run { root.text = header.value }

    override fun getDefaultLayout() = R.layout.item_header_view

    inner class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
            binding = ItemHeaderViewBinding.bind(itemView)
        }
    }
}
