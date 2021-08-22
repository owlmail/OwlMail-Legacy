package github.sachin2dehury.owlmail.epoxy.controller

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.google.android.gms.ads.AdSize
import github.sachin2dehury.owlmail.epoxy.UiModel
import github.sachin2dehury.owlmail.epoxy.model.ItemAdModel
import github.sachin2dehury.owlmail.epoxy.model.ItemCircularLoaderModel
import github.sachin2dehury.owlmail.epoxy.model.ItemHeaderModel
import github.sachin2dehury.owlmail.epoxy.model.ItemHorizontalLoaderModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
abstract class BaseController<T> : PagingDataEpoxyController<UiModel<T>>() {

    private val code by lazy { hashCode() }

    override fun buildItemModel(currentPosition: Int, item: UiModel<T>?): EpoxyModel<*> {
        return item?.let {
            when (it) {
                is UiModel.Header -> addHeader(it)
                is UiModel.Item -> addItem(it)
                is UiModel.Footer -> addFooter(it)
                is UiModel.Ad -> addAds(it)
                is UiModel.Loader -> addLoader(it)
                is UiModel.Empty -> addElse()
                is UiModel.Error -> addElse()
            }
        } ?: addElse()
    }

    abstract fun addItem(uiModel: UiModel.Item<T>): EpoxyModel<*>

    open fun addHeader(uiModel: UiModel.Header): EpoxyModel<*> =
        ItemHeaderModel(uiModel.value).id("header_${uiModel.value}")

    abstract fun addFooter(uiModel: UiModel.Footer): EpoxyModel<*>

    private fun addAds(uiModel: UiModel.Ad) = ItemAdModel(uiModel.adSize).id("ad_$code")

    private fun addElse(): EpoxyModel<*> = ItemAdModel(AdSize.BANNER).id("ad_$code")

    private fun addLoader(uiModel: UiModel.Loader) =
        ItemHorizontalLoaderModel(uiModel.value).id("loader_$code")

    private fun addCircularLoader(uiModel: UiModel.Loader) =
        ItemCircularLoaderModel(uiModel.value).id("loader_$code")
}