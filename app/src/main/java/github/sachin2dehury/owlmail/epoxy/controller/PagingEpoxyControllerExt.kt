package github.sachin2dehury.owlmail.epoxy.controller

import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.google.android.gms.ads.AdSize
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.UiModel
import github.sachin2dehury.owlmail.epoxy.model.*

abstract class PagingEpoxyControllerExt<T>(val listener: EpoxyModelOnClickListener?) :
    PagingDataEpoxyController<UiModel<T>>() {

    val controllerHashCode by lazy { hashCode() }

    override fun buildItemModel(currentPosition: Int, item: UiModel<T>?): EpoxyModel<View> =
        item?.let {
            when (it) {
                is UiModel.Header -> addHeader(it)
                is UiModel.Item -> addItem(it)
                is UiModel.Footer -> addFooter(it)
                is UiModel.Ad -> addAds(it)
                is UiModel.Loader -> addLoader(it)
                is UiModel.Empty -> addEmpty(it)
                is UiModel.Error -> addError(it)
            }
        } ?: addElse()

    abstract fun addItem(uiModel: UiModel.Item<T>): EpoxyModel<View>

    open fun addHeader(uiModel: UiModel.Header): EpoxyModel<View> =
        ItemHeaderModel(uiModel, listener).id("header_$controllerHashCode : ${uiModel.value}")

    private fun addFooter(uiModel: UiModel.Footer) =
        ItemFooterModel(uiModel, listener).id("footer_$controllerHashCode")

    private fun addAds(uiModel: UiModel.Ad) =
        ItemAdModel(uiModel, listener).id("ad_$controllerHashCode")

    private fun addEmpty(uiModel: UiModel.Empty) =
        ItemEmptyModel(uiModel, listener).id("empty_$controllerHashCode")

    private fun addError(uiModel: UiModel.Error) =
        ItemErrorModel(uiModel, listener).id("error_$controllerHashCode")

    private fun addElse() =
        ItemAdModel(UiModel.Ad(AdSize.BANNER), listener).id("ad_$controllerHashCode")

    private fun addLoader(uiModel: UiModel.Loader) =
        ItemCircularLoaderModel(uiModel, listener).id("loader_$controllerHashCode")

}