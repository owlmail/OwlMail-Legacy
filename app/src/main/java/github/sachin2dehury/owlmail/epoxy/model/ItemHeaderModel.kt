package github.sachin2dehury.owlmail.epoxy.model

import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemHeaderViewBinding
import github.sachin2dehury.owlmail.epoxy.ViewBindingKotlinModel

data class ItemHeaderModel(val value: String) :
    ViewBindingKotlinModel<ItemHeaderViewBinding>(R.layout.item_header_view) {

    override fun ItemHeaderViewBinding.bind() {
        root.text = value
    }
}