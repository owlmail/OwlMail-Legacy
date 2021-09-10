package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import com.google.android.gms.ads.AdRequest
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemAdViewBinding
import github.sachin2dehury.owlmail.epoxy.EpoxyModelExt
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.UiModel

class ItemAdModel(private val uiModel: UiModel.Ad, listener: EpoxyModelOnClickListener? = null) :
    EpoxyModelExt<ItemAdViewBinding>(R.layout.item_ad_view, listener) {

    override fun bindExt(view: View) = ItemAdViewBinding.bind(view).apply {
//        root.adSize = uiModel.adSize
        root.loadAd(AdRequest.Builder().build())
        setOnModelClickListener(uiModel)
    }
}