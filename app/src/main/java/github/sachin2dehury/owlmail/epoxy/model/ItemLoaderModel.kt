package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemLoaderViewBinding
import github.sachin2dehury.owlmail.epoxy.EpoxyModelExt
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.UiModel

class ItemLoaderModel(
    private val uiModel: UiModel.Loader,
    listener: EpoxyModelOnClickListener? = null
) : EpoxyModelExt<ItemLoaderViewBinding>(R.layout.item_loader_view, listener) {

    override fun bindExt(view: View) = ItemLoaderViewBinding.bind(view).apply {
        if (uiModel.value) {
            root.progress = 100
        }
    }
}