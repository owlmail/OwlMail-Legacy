package github.sachin2dehury.owlmail.epoxy.model

import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemMailViewBinding
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.epoxy.ViewBindingKotlinModel
import github.sachin2dehury.owlmail.other.getFirstCharacter
import github.sachin2dehury.owlmail.other.getFormattedDate
import github.sachin2dehury.owlmail.ui.utils.getStyleFromAttr
import github.sachin2dehury.owlmail.ui.utils.setBackgroundColor
import github.sachin2dehury.owlmail.ui.utils.show

data class ItemMailModel(val mail: Mail) :
    ViewBindingKotlinModel<ItemMailViewBinding>(R.layout.item_mail_view) {

    override fun ItemMailViewBinding.bind() {
        val sender =
            if (mail.flags?.contains('s') == true) mail.addressList?.first() else mail.addressList?.last()
        senderNameText.text = sender?.firstName
        senderIconText.text = sender?.firstName?.getFirstCharacter()
        senderIconText.setBackgroundColor()
        mailBody.text = mail.body
        mailSubject.text = mail.subject
        dateText.text = getFormattedDate(mail.time ?: 0, root.context)

        when {
            mail.flags?.contains('u') == true -> {
                senderNameText.setTextAppearance(
                    root.context,
                    root.getStyleFromAttr(R.attr.textAppearanceTitle5)
                )
            }
            mail.flags?.contains('f') == true -> staredIcon.show()
            mail.flags?.contains('a') == true -> attachmentIcon.show()
//            mail.flags?.contains('r') == true -> TODO
        }
    }
}