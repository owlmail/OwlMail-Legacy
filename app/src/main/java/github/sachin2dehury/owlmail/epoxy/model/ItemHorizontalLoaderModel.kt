package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemHorizontalLoaderViewBinding
import github.sachin2dehury.owlmail.epoxy.EpoxyModelExt
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.UiModel

class ItemHorizontalLoaderModel(
    private val uiModel: UiModel.Loader,
    listener: EpoxyModelOnClickListener? = null
) : EpoxyModelExt<ItemHorizontalLoaderViewBinding>(R.layout.item_horizontal_loader_view, listener) {

    override fun bindExt(view: View) = ItemHorizontalLoaderViewBinding.bind(view).apply {
        if (uiModel.value) {
            root.cancelAnimation()
        }
    }
}