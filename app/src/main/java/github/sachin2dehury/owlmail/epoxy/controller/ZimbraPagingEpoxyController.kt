package github.sachin2dehury.owlmail.epoxy.controller

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import github.sachin2dehury.owlmail.data.local.Header
import github.sachin2dehury.owlmail.data.remote.search.Conversation
import github.sachin2dehury.owlmail.data.remote.searchconv.Message
import github.sachin2dehury.owlmail.data.remote.searchgal.Contact
import github.sachin2dehury.owlmail.epoxy.model.CircularLoaderModel_
import github.sachin2dehury.owlmail.epoxy.model.ContactModel_
import github.sachin2dehury.owlmail.epoxy.model.ConversationModel_
import github.sachin2dehury.owlmail.epoxy.model.HeaderModel_
import github.sachin2dehury.owlmail.epoxy.model.LinearLoaderModel_
import github.sachin2dehury.owlmail.epoxy.model.MessageModel_

class ZimbraPagingEpoxyController<T : Any> : PagingDataEpoxyController<T>(
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

    override fun buildItemModel(currentPosition: Int, item: T?): EpoxyModel<*> = when (item) {
        is Conversation -> ConversationModel_()
            .id(item.id)
            .conversation(item)
        is Message -> MessageModel_()
            .id(item.id)
            .message(item)
        is Contact -> ContactModel_()
            .id(item.id)
            .contact(item)
        is Header -> HeaderModel_()
            .id(item.value)
            .header(item)
        else -> CircularLoaderModel_().id("circular_loader")
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        models.toMutableList().run {
            val model = when {
                isEmptyState -> CircularLoaderModel_().id("circular_loader")
                isErrorState -> CircularLoaderModel_().id("circular_loader")
                isLastItem -> CircularLoaderModel_().id("circular_loader")
                isLoadingState -> CircularLoaderModel_().id("circular_loader")
                else -> LinearLoaderModel_().id("linear_loader")
            }
            add(model)
            removeAll { !it.isShown }
        }
        super.addModels(models)
    }
}
