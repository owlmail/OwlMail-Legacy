package github.sachin2dehury.owlmail.epoxy.model

import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.getFormattedDate
import github.sachin2dehury.owlmail.databinding.ItemMailViewBinding
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.epoxy.ViewBindingKotlinModel

data class ItemMailModel(val mail: Mail) :
    ViewBindingKotlinModel<ItemMailViewBinding>(R.layout.item_mail_view) {

    override fun ItemMailViewBinding.bind() {
        senderEmail.text = mail.addressList?.last()?.email
        senderName.text = mail.addressList?.last()?.firstName?.first().toString()
        mailBody.text = mail.body
        mailSubject.text = mail.subject
        date.text = getFormattedDate(mail.time ?: 0, root.context)
    }
}