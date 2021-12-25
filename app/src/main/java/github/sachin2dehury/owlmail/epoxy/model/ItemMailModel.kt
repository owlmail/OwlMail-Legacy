package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemMailViewBinding
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.epoxy.EpoxyModelExt
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.UiModel
import github.sachin2dehury.owlmail.utils.getFirstCharacter
import github.sachin2dehury.owlmail.utils.getFormattedDate
import github.sachin2dehury.owlmail.utils.setBackgroundColor
import github.sachin2dehury.owlmail.utils.show

class ItemMailModel(
    private val uiModel: UiModel.Item<Mail>,
    listener: EpoxyModelOnClickListener? = null
) : EpoxyModelExt<ItemMailViewBinding>(R.layout.item_mail_view, listener) {

    override fun bindExt(view: View) = ItemMailViewBinding.bind(view).apply {
        val mail = uiModel.value
        val sender =
            if (mail.flags?.contains('s') == true) mail.addressList?.first() else mail.addressList?.last()
        senderNameText.text = sender?.firstName
        senderIconText.text = sender?.firstName?.getFirstCharacter()
        senderIconText.setBackgroundColor()
        mailBody.text = mail.body
        mailSubject.text = mail.subject
        dateText.text = getFormattedDate(mail.time, root.context)

        when {
            mail.flags?.contains('u') == true -> {
            }
            mail.flags?.contains('f') == true -> staredIcon.show()
            mail.flags?.contains('a') == true -> attachmentIcon.show()
//            mail.flags?.contains('r') == true -> TODO
        }
        setOnModelClickListener(uiModel)
    }
}