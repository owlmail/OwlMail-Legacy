package github.sachin2dehury.owlmail.epoxy.model

import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemCircularLoaderViewBinding
import github.sachin2dehury.owlmail.epoxy.ViewBindingKotlinModel

data class ItemCircularLoaderModel(val value: Boolean) :
    ViewBindingKotlinModel<ItemCircularLoaderViewBinding>(R.layout.item_circular_loader_view) {

    override fun ItemCircularLoaderViewBinding.bind() {
        if (value) {
            root.progress = 100
        }
    }
}