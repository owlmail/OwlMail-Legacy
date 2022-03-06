package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.data.remote.search.Conversation
import github.sachin2dehury.owlmail.databinding.ItemConversationViewBinding
import github.sachin2dehury.owlmail.utils.getFirstCharacter
import github.sachin2dehury.owlmail.utils.getFormattedDate
import github.sachin2dehury.owlmail.utils.getFrom
import github.sachin2dehury.owlmail.utils.getName
import github.sachin2dehury.owlmail.utils.hasAttachments
import github.sachin2dehury.owlmail.utils.isStared

@EpoxyModelClass
abstract class ConversationModel : EpoxyModelWithHolder<ConversationModel.Holder>() {

    @EpoxyAttribute
    var conversation: Conversation? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onClickListener: View.OnClickListener? = null

    private var binding: ItemConversationViewBinding? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        conversation?.let { render(it) }
        binding?.root?.setOnClickListener(onClickListener)
    }

    override fun unbind(holder: Holder) {
        super.unbind(holder)
        binding?.root?.setOnClickListener(null)
        binding = null
    }

    private fun render(conversation: Conversation) = binding?.run {
        val fromName = conversation.emails?.getFrom()?.getName()
        messageSenderTv.text = fromName
        messageSubjectTv.text = conversation.subject
        messageBodyTv.text = conversation.body
        messageDateTv.text = conversation.date?.getFormattedDate(root.context)
        messageSenderIconTv.text = fromName?.getFirstCharacter()
        messageAttachmentIcon.isVisible = conversation.flags?.hasAttachments() ?: false
        messageFlagIcon.isVisible = conversation.flags?.isStared() ?: false
    }

    override fun getDefaultLayout() = R.layout.item_conversation_view

    inner class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
            binding = ItemConversationViewBinding.bind(itemView)
        }
    }
}
