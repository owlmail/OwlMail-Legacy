package github.sachin2dehury.owlmail.epoxy.model

import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemHorizontalLoaderViewBinding
import github.sachin2dehury.owlmail.epoxy.ViewBindingKotlinModel

data class ItemHorizontalLoaderModel(val value: Boolean) :
    ViewBindingKotlinModel<ItemHorizontalLoaderViewBinding>(R.layout.item_horizontal_loader_view) {

    override fun ItemHorizontalLoaderViewBinding.bind() {
        if (value) {
            root.cancelAnimation()
        }
    }
}