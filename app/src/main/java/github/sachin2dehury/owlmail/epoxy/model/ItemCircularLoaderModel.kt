package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemCircularLoaderViewBinding
import github.sachin2dehury.owlmail.epoxy.EpoxyModelExt
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.UiModel

class ItemCircularLoaderModel(
    private val uiModel: UiModel.Loader,
    listener: EpoxyModelOnClickListener? = null
) : EpoxyModelExt<ItemCircularLoaderViewBinding>(R.layout.item_circular_loader_view, listener) {

    override fun bindExt(view: View) = ItemCircularLoaderViewBinding.bind(view).apply {
        if (uiModel.value) {
            root.progress = 100
        }
    }
}