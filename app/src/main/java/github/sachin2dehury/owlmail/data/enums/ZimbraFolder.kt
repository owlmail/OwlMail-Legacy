package github.sachin2dehury.owlmail.data.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ZimbraFolder(val index: Int, val value: String) : Parcelable {
    @Parcelize
    object INBOX : ZimbraFolder(2, "inbox")

    @Parcelize
    object TRASH : ZimbraFolder(3, "trash")

    @Parcelize
    object JUNK : ZimbraFolder(4, "junk")

    @Parcelize
    object SENT : ZimbraFolder(5, "sent")

    @Parcelize
    object DRAFT : ZimbraFolder(6, "draft")

    fun fromIndex(index: Int?) = when (index) {
        ZimbraFolder.INBOX.index -> ZimbraFolder.INBOX
        ZimbraFolder.SENT.index -> ZimbraFolder.SENT
        ZimbraFolder.DRAFT.index -> ZimbraFolder.DRAFT
        ZimbraFolder.JUNK.index -> ZimbraFolder.JUNK
        ZimbraFolder.TRASH.index -> ZimbraFolder.TRASH
        else -> ZimbraFolder.INBOX
    }

    fun fromString(value: String?) = when (value) {
        ZimbraFolder.INBOX.value -> ZimbraFolder.INBOX
        ZimbraFolder.SENT.value -> ZimbraFolder.SENT
        ZimbraFolder.DRAFT.value -> ZimbraFolder.DRAFT
        ZimbraFolder.JUNK.value -> ZimbraFolder.JUNK
        ZimbraFolder.TRASH.value -> ZimbraFolder.TRASH
        else -> ZimbraFolder.INBOX
    }

    fun mapToQuery() = "in:$value"
}
