package github.sachin2dehury.owlmail.epoxy.controller

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.epoxy.EpoxyModelExt
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.paging.ZimbraPagingType

class ZimbraPagingEpoxyController(
    private val listener: EpoxyModelOnClickListener<ZimbraPagingType>,
) : PagingDataEpoxyController<ZimbraPagingType>(
    modelBuildingHandler = EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    diffingHandler = EpoxyAsyncUtil.getAsyncBackgroundHandler(),
) {

    var isLoadingState = false
    var isErrorState = false
    var isEmptyState = false
    var isLastItem = false

    override fun buildItemModel(currentPosition: Int, item: ZimbraPagingType?) = when (item) {
        is ZimbraPagingType.SearchRequest -> dummy()
        is ZimbraPagingType.SearchConvRequest -> dummy()
        is ZimbraPagingType.SearchGalRequest -> dummy()
        else -> dummy()
    }

    private fun dummy() = EpoxyModelExt(
        null,
        R.layout.item_header_view,
        listener
    )

    override fun addModels(models: List<EpoxyModel<*>>) {
        models.toMutableList().run {
            removeAll { !it.isShown }
//            when{
//                isEmptyState ->
//                isErrorState ->
//                isLastItem ->
//                isLoadingState->
//            }
        }
        super.addModels(models)
    }

}