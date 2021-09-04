package github.sachin2dehury.owlmail.epoxy.model

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemAdViewBinding
import github.sachin2dehury.owlmail.epoxy.ViewBindingKotlinModel

data class ItemAdModel(val adSize: AdSize) :
    ViewBindingKotlinModel<ItemAdViewBinding>(R.layout.item_ad_view) {

    override fun ItemAdViewBinding.bind() {
//        root.adSize = adSize
        root.loadAd(AdRequest.Builder().build())
    }
}