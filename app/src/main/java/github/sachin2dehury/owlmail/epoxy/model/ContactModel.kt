package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.data.remote.searchgal.Contact
import github.sachin2dehury.owlmail.databinding.ItemMessageViewBinding
import github.sachin2dehury.owlmail.utils.getFirstCharacter
import github.sachin2dehury.owlmail.utils.getName

@EpoxyModelClass
abstract class ContactModel : EpoxyModelWithHolder<ContactModel.Holder>() {

    @EpoxyAttribute
    var contact: Contact? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onClickListener: View.OnClickListener? = null

    private var binding: ItemMessageViewBinding? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        contact?.let { render(it) }
        binding?.root?.setOnClickListener(onClickListener)
    }

    override fun unbind(holder: Holder) {
        super.unbind(holder)
        binding?.root?.setOnClickListener(null)
        binding = null
    }

    private fun render(contact: Contact) = binding?.run {
        val name = contact.attrs?.getName()
        messageSenderTv.text = name
        messageSubjectTv.text = contact.attrs?.email
        messageBodyTv.text = contact.attrs?.email2
//        messageDateTv.text = contact.attrs?.createTimeStamp
        messageSenderIconTv.text = name?.getFirstCharacter()
        messageAttachmentIcon.isVisible = false
        messageFlagIcon.isVisible = false
    }

    override fun getDefaultLayout() = R.layout.item_message_view

    inner class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
            binding = ItemMessageViewBinding.bind(itemView)
        }
    }
}
