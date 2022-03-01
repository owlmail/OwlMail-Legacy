package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.data.remote.searchconv.Message
import github.sachin2dehury.owlmail.databinding.ItemMessageViewBinding
import github.sachin2dehury.owlmail.utils.getFirstCharacter
import github.sachin2dehury.owlmail.utils.getFrom
import github.sachin2dehury.owlmail.utils.getName
import github.sachin2dehury.owlmail.utils.hasAttachments
import github.sachin2dehury.owlmail.utils.isStared

@EpoxyModelClass
abstract class MessageModel : EpoxyModelWithHolder<MessageModel.Holder>() {

    @EpoxyAttribute
    var message: Message? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onClickListener: View.OnClickListener? = null

    private var binding: ItemMessageViewBinding? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        message?.let { render(it) }
        binding?.root?.setOnClickListener(onClickListener)
    }

    override fun unbind(holder: Holder) {
        super.unbind(holder)
        binding?.root?.setOnClickListener(null)
        binding = null
    }

    private fun render(message: Message) = binding?.run {
        val fromName = message.email?.getFrom()?.getName()
        messageSenderTv.text = fromName
        messageSubjectTv.text = message.subject
        messageBodyTv.text = message.body
//        messageDateTv.text = message.date?.getFormattedDate(root.context)
        messageSenderIconTv.text = fromName?.getFirstCharacter()
        messageAttachmentIcon.isVisible = message.flags?.hasAttachments() ?: false
        messageFlagIcon.isVisible = message.flags?.isStared() ?: false
    }

    override fun getDefaultLayout() = R.layout.item_message_view

    inner class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
            binding = ItemMessageViewBinding.bind(itemView)
        }
    }
}
