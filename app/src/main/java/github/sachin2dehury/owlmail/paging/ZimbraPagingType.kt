package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.data.search.Conversation
import github.sachin2dehury.owlmail.data.searchconv.Message
import github.sachin2dehury.owlmail.data.searchgal.Contact

sealed class ZimbraPagingType {
    data class SearchRequest(val value: Conversation?) : ZimbraPagingType()
    data class SearchGalRequest(val value: Contact?) : ZimbraPagingType()
    data class SearchConvRequest(val value: Message?) : ZimbraPagingType()
}