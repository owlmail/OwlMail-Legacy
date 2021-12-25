package github.sachin2dehury.owlmail.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.auth.AuthRequest
import github.sachin2dehury.owlmail.data.auth.AuthResponse
import github.sachin2dehury.owlmail.data.autocomplete.AutoCompleteRequest
import github.sachin2dehury.owlmail.data.autocomplete.AutoCompleteResponse
import github.sachin2dehury.owlmail.data.convaction.ConvAction
import github.sachin2dehury.owlmail.data.getcontact.GetContactsRequest
import github.sachin2dehury.owlmail.data.getcontact.GetContactsResponse
import github.sachin2dehury.owlmail.data.getmessage.GetMsgRequest
import github.sachin2dehury.owlmail.data.getmessage.GetMsgResponse
import github.sachin2dehury.owlmail.data.savedraft.SaveDraftRequest
import github.sachin2dehury.owlmail.data.savedraft.SaveDraftResponse
import github.sachin2dehury.owlmail.data.search.SearchRequest
import github.sachin2dehury.owlmail.data.search.SearchResponse
import github.sachin2dehury.owlmail.data.searchconv.SearchConvRequest
import github.sachin2dehury.owlmail.data.searchconv.SearchConvResponse
import github.sachin2dehury.owlmail.data.searchgal.SearchGalRequest
import github.sachin2dehury.owlmail.data.searchgal.SearchGalResponse
import github.sachin2dehury.owlmail.data.sendmsg.SendMsgRequest
import github.sachin2dehury.owlmail.data.sendmsg.SendMsgResponse
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Body(
    @Json(name = "AuthRequest")
    val authRequest: AuthRequest? = null,
    @Json(name = "AuthResponse")
    val authResponse: AuthResponse? = null,

    @Json(name = "AutoCompleteRequest")
    val autoCompleteRequest: AutoCompleteRequest? = null,
    @Json(name = "AutoCompleteResponse")
    val autoCompleteResponse: AutoCompleteResponse? = null,

    @Json(name = "ConvActionRequest")
    val convActionRequest: ConvAction? = null,

    @Json(name = "ConvActionResponse")
    val convActionResponse: ConvAction? = null,

    @Json(name = "Fault")
    val fault: Fault? = null,

    @Json(name = "GetContactsRequest")
    val getContactsRequest: GetContactsRequest? = null,

    @Json(name = "GetContactsResponse")
    val getContactsResponse: GetContactsResponse? = null,

    @Json(name = "GetMsgRequest")
    val getMsgRequest: GetMsgRequest? = null,

    @Json(name = "GetMsgResponse")
    val getMsgResponse: GetMsgResponse? = null,

    @Json(name = "SaveDraftRequest")
    val saveDraftRequest: SaveDraftRequest? = null,

    @Json(name = "SaveDraftResponse")
    val saveDraftResponse: SaveDraftResponse? = null,

    @Json(name = "SearchConvRequest")
    val searchConvRequest: SearchConvRequest? = null,

    @Json(name = "SearchConvResponse")
    val searchConvResponse: SearchConvResponse? = null,

    @Json(name = "SearchGalRequest")
    val searchGalRequest: SearchGalRequest? = null,

    @Json(name = "SearchGalResponse")
    val searchGalResponse: SearchGalResponse? = null,

    @Json(name = "SearchRequest")
    val searchRequest: SearchRequest? = null,

    @Json(name = "SearchResponse")
    val searchResponse: SearchResponse? = null,

    @Json(name = "SendMsgRequest")
    val sendMsgRequest: SendMsgRequest? = null,

    @Json(name = "SendMsgResponse")
    val sendMsgResponse: SendMsgResponse? = null,
) : Parcelable {

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Fault(
        @Json(name = "Reason")
        val reason: Reason? = null
    ) : Parcelable {

        @JsonClass(generateAdapter = true)
        @Parcelize
        data class Reason(
            @Json(name = "Text")
            val text: String? = null
        ) : Parcelable
    }
}