package github.sachin2dehury.owlmail.epoxy.controller

import android.view.View
import com.airbnb.epoxy.EpoxyModel
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.UiModel
import github.sachin2dehury.owlmail.epoxy.model.ItemMailModel

class MailBoxController(listener: EpoxyModelOnClickListener) : PagingEpoxyControllerExt<Mail>(listener) {

    override fun addItem(uiModel: UiModel.Item<Mail>): EpoxyModel<View> =
        ItemMailModel(uiModel,listener).id("mail_$controllerHashCode : ${uiModel.value.id}")
}