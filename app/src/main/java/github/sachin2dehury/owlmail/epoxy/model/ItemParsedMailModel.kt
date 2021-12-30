package github.sachin2dehury.owlmail.epoxy.model

//class ItemParsedMailModel(
//    private val epoxyUiModel: EpoxyUiModel.Item<Mail>,
//    listener: EpoxyModelOnClickListener? = null
//) : EpoxyModelExt<ItemParsedMailBinding>(R.layout.item_parsed_mail, listener) {
//
//    override fun bindExt(view: View) = ItemParsedMailBinding.bind(view).apply {
//        val mail = epoxyUiModel.value
//        val sender =
//            if (mail.flags?.contains('s') == true) mail.addressList?.first() else mail.addressList?.last()
//        senderNameText.text = sender?.firstName
//        senderIconText.text = sender?.firstName?.getFirstCharacter()
//        senderIconText.setBackgroundColor()
////        mailBody.text = mail.body
//        mailSubject.text = mail.subject
////        dateText.text = getFormattedDate(mail.time ?: 0, root.context)
//
//        when {
//            mail.flags?.contains('u') == true -> {
//
//            }
//            mail.flags?.contains('f') == true -> staredIcon.show()
//            mail.flags?.contains('a') == true -> attachmentIcon.show()
////            mail.flags?.contains('r') == true -> TODO
//        }
//        setOnModelClickListener(epoxyUiModel)
//    }
//}