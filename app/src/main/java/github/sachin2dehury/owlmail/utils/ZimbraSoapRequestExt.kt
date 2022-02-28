package github.sachin2dehury.owlmail.utils

import github.sachin2dehury.owlmail.data.Body
import github.sachin2dehury.owlmail.data.ZimbraSoap
import github.sachin2dehury.owlmail.data.auth.AuthRequest
import github.sachin2dehury.owlmail.data.autocomplete.AutoCompleteRequest
import github.sachin2dehury.owlmail.data.common.Content
import github.sachin2dehury.owlmail.data.common.MsgId
import github.sachin2dehury.owlmail.data.convaction.ConvAction
import github.sachin2dehury.owlmail.data.getcontact.GetContactsRequest
import github.sachin2dehury.owlmail.data.getmessage.GetMsgRequest
import github.sachin2dehury.owlmail.data.local.UserDetails
import github.sachin2dehury.owlmail.data.search.SearchRequest
import github.sachin2dehury.owlmail.data.searchconv.SearchConvRequest
import github.sachin2dehury.owlmail.data.searchgal.SearchGalRequest

fun getZimbraAuthRequest(userDetails: UserDetails?) = ZimbraSoap(
    Body(
        authRequest = AuthRequest(
            account = Content(userDetails?.username),
            password = Content(userDetails?.password),
        )
    )
)

fun getZimbraAutoCompleteRequest(query: String?) = ZimbraSoap(
    Body(
        autoCompleteRequest = AutoCompleteRequest(
            name = Content(query)
        )
    )
)

fun getZimbraConvActionRequest(operation: String?, ids: List<String?>?) = ZimbraSoap(
    Body(
        convActionRequest = ConvAction(
            action = ConvAction.Action(
                op = operation,
                id = ids?.joinToString(),
            )
        )
    )
)

fun getZimbraGetContactsRequest() = ZimbraSoap(Body(getContactsRequest = GetContactsRequest()))

fun getZimbraGetMsgRequest(id: String?) = ZimbraSoap(
    Body(
        getMsgRequest = GetMsgRequest(msgId = MsgId(id = id))
    )
)

// fun getZimbraSaveDraftRequest() = ZimbraSoap(
//    Body(
//        saveDraftRequest = SaveDraftRequest()
//    )
// )

fun getZimbraSearchRequest(query: String?, offset: Int?, limit: Int?) = ZimbraSoap(
    Body(
        searchRequest = SearchRequest(
            query = query,
            offset = offset,
            limit = limit,
        )
    )
)

fun getZimbraSearchConvRequest(conversationId: String?, offset: Int?, limit: Int?) = ZimbraSoap(
    Body(
        searchConvRequest = SearchConvRequest(
            cid = conversationId,
            offset = offset,
            limit = limit,
        )
    )
)

fun getZimbraSearchGalRequest(query: String?, offset: Int?, limit: Int?) = ZimbraSoap(
    Body(
        searchGalRequest = SearchGalRequest(
            name = query,
            offset = offset,
            limit = limit,
        )
    )
)

// fun getZimbraSendMsgRequest() = ZimbraSoap(
//    Body(
//        sendMsgRequest = SendMsgRequest()
//    )
// )
