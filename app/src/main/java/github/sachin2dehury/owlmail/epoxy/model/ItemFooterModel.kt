package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemHeaderViewBinding
import github.sachin2dehury.owlmail.epoxy.EpoxyModelExt
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.UiModel

class ItemFooterModel(
    private val uiModel: UiModel.Footer,
    listener: EpoxyModelOnClickListener? = null
) : EpoxyModelExt<ItemHeaderViewBinding>(R.layout.item_header_view, listener) {

    override fun bindExt(view: View) = ItemHeaderViewBinding.bind(view).apply {
        root.text = uiModel.value.toString()
    }
}