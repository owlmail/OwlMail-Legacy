package github.sachin2dehury.owlmail.utils

import github.sachin2dehury.owlmail.data.constants.ZimbraFolder

fun ZimbraFolder.fromIndex(index: Int?) = when (index) {
    ZimbraFolder.INBOX.index -> ZimbraFolder.INBOX
    ZimbraFolder.SENT.index -> ZimbraFolder.SENT
    ZimbraFolder.DRAFT.index -> ZimbraFolder.DRAFT
    ZimbraFolder.JUNK.index -> ZimbraFolder.JUNK
    ZimbraFolder.TRASH.index -> ZimbraFolder.TRASH
    else -> ZimbraFolder.INBOX
}

fun ZimbraFolder.fromString(value: String?) = when (value) {
    ZimbraFolder.INBOX.value -> ZimbraFolder.INBOX
    ZimbraFolder.SENT.value -> ZimbraFolder.SENT
    ZimbraFolder.DRAFT.value -> ZimbraFolder.DRAFT
    ZimbraFolder.JUNK.value -> ZimbraFolder.JUNK
    ZimbraFolder.TRASH.value -> ZimbraFolder.TRASH
    else -> ZimbraFolder.INBOX
}