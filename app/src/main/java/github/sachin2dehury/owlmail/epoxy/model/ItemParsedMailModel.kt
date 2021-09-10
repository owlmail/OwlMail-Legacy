package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.ItemParsedMailBinding
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.epoxy.EpoxyModelExt
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.UiModel
import github.sachin2dehury.owlmail.other.getFirstCharacter
import github.sachin2dehury.owlmail.ui.utils.getStyleFromAttr
import github.sachin2dehury.owlmail.ui.utils.setBackgroundColor
import github.sachin2dehury.owlmail.ui.utils.show

class ItemParsedMailModel(
    private val uiModel: UiModel.Item<Mail>,
    listener: EpoxyModelOnClickListener? = null
) : EpoxyModelExt<ItemParsedMailBinding>(R.layout.item_parsed_mail, listener) {

    override fun bindExt(view: View) = ItemParsedMailBinding.bind(view).apply {
        val mail = uiModel.value
        val sender =
            if (mail.flags?.contains('s') == true) mail.addressList?.first() else mail.addressList?.last()
        senderNameText.text = sender?.firstName
        senderIconText.text = sender?.firstName?.getFirstCharacter()
        senderIconText.setBackgroundColor()
//        mailBody.text = mail.body
        mailSubject.text = mail.subject
//        dateText.text = getFormattedDate(mail.time ?: 0, root.context)

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
        setOnModelClickListener(uiModel)
    }
}