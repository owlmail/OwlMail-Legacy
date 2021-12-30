package github.sachin2dehury.owlmail.epoxy.model

//class ItemMailModel(
//    private val epoxyUiModel: EpoxyUiModel.Item<Mail>,
//    listener: EpoxyModelOnClickListener? = null
//) : EpoxyModelExt<ItemMailViewBinding>(R.layout.item_mail_view, listener) {
//
//    override fun bindExt(view: View) = ItemMailViewBinding.bind(view).apply {
//        val mail = epoxyUiModel.value
//        val sender =
//            if (mail.flags?.contains('s') == true) mail.addressList?.first() else mail.addressList?.last()
//        senderNameText.text = sender?.firstName
//        senderIconText.text = sender?.firstName?.getFirstCharacter()
//        senderIconText.setBackgroundColor()
//        mailBody.text = mail.body
//        mailSubject.text = mail.subject
//        dateText.text = getFormattedDate(mail.time, root.context)
//
//        when {
//            mail.flags?.contains('u') == true -> {
//            }
//            mail.flags?.contains('f') == true -> staredIcon.show()
//            mail.flags?.contains('a') == true -> attachmentIcon.show()
////            mail.flags?.contains('r') == true -> TODO
//        }
//        setOnModelClickListener(epoxyUiModel)
//    }
//}