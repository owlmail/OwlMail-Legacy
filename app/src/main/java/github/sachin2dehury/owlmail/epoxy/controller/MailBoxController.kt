package github.sachin2dehury.owlmail.epoxy.controller

import com.airbnb.epoxy.EpoxyModel
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.epoxy.model.ItemMailModel
import github.sachin2dehury.owlmail.epoxy.model.UiModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class MailBoxController : BaseController<Mail>() {

    override fun addItem(uiModel: UiModel.Item<Mail>): EpoxyModel<*> =
        ItemMailModel(uiModel.value).id("mail_${uiModel.value.id}")

    override fun addHeader(uiModel: UiModel.Header): EpoxyModel<*> {
        TODO("Not yet implemented")
    }

    override fun addFooter(uiModel: UiModel.Footer): EpoxyModel<*> {
        TODO("Not yet implemented")
    }
}