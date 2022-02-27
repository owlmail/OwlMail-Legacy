package github.sachin2dehury.owlmail.epoxy.controller

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.data.search.Conversation
import github.sachin2dehury.owlmail.data.searchconv.Message
import github.sachin2dehury.owlmail.data.searchgal.Contact
import github.sachin2dehury.owlmail.epoxy.EpoxyModelExt
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener

class ZimbraPagingEpoxyController<T : Any>(
    private val listener: EpoxyModelOnClickListener<T>,
) : PagingDataEpoxyController<T>(
    modelBuildingHandler = EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    diffingHandler = EpoxyAsyncUtil.getAsyncBackgroundHandler(),
) {

    var isLoadingState = false
    var isErrorState = false
    var isEmptyState = false
    var isLastItem = false

    override fun isStickyHeader(position: Int): Boolean {
        return adapter.getModelAtPosition(position).layout == 0
    }

    override fun buildItemModel(currentPosition: Int, item: T?) = when (item) {
        is Conversation -> dummy()
        is Message -> dummy()
        is Contact -> dummy()
        else -> dummy().hide()
    }

    private fun dummy() = EpoxyModelExt(
        null,
        R.layout.item_header_view,
        listener
    )

    override fun addModels(models: List<EpoxyModel<*>>) {
        models.toMutableList().run {
            val model = when {
                isEmptyState -> dummy()
                isErrorState -> dummy()
                isLastItem -> dummy()
                isLoadingState -> dummy()
                else -> dummy().hide()
            }
            add(model)
            removeAll { !it.isShown }
        }
        super.addModels(models)
    }
}
