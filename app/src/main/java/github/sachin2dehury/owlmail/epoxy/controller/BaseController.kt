package github.sachin2dehury.owlmail.epoxy.controller

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.google.android.gms.ads.AdSize
import github.sachin2dehury.owlmail.epoxy.model.ItemAdModel
import github.sachin2dehury.owlmail.epoxy.model.UiModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
abstract class BaseController<T> : PagingDataEpoxyController<UiModel<T>>() {
    override fun buildItemModel(currentPosition: Int, item: UiModel<T>?): EpoxyModel<*> {
        return item?.let {
            when (it) {
                is UiModel.Header -> addHeader(it)
                is UiModel.Item -> addItem(it)
                is UiModel.Footer -> addFooter(it)
                is UiModel.Ad -> addAds(it)
            }
        } ?: addElse()
    }

    abstract fun addItem(uiModel: UiModel.Item<T>): EpoxyModel<*>

    abstract fun addHeader(uiModel: UiModel.Header): EpoxyModel<*>

    abstract fun addFooter(uiModel: UiModel.Footer): EpoxyModel<*>

    private fun addAds(uiModel: UiModel.Ad) = ItemAdModel(uiModel.adSize).id("ad_${hashCode()}")

    private fun addElse(): EpoxyModel<*> = ItemAdModel(AdSize.BANNER).id("ad_${hashCode()}")
}