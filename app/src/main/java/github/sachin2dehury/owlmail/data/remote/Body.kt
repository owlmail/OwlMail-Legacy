package github.sachin2dehury.owlmail.data.remote

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.remote.auth.AuthRequest
import github.sachin2dehury.owlmail.data.remote.auth.AuthResponse
import github.sachin2dehury.owlmail.data.remote.autocomplete.AutoCompleteRequest
import github.sachin2dehury.owlmail.data.remote.autocomplete.AutoCompleteResponse
import github.sachin2dehury.owlmail.data.remote.convaction.ConvAction
import github.sachin2dehury.owlmail.data.remote.getcontact.GetContactsRequest
import github.sachin2dehury.owlmail.data.remote.getcontact.GetContactsResponse
import github.sachin2dehury.owlmail.data.remote.getmessage.GetMsgRequest
import github.sachin2dehury.owlmail.data.remote.getmessage.GetMsgResponse
import github.sachin2dehury.owlmail.data.remote.savedraft.SaveDraftRequest
import github.sachin2dehury.owlmail.data.remote.savedraft.SaveDraftResponse
import github.sachin2dehury.owlmail.data.remote.search.SearchRequest
import github.sachin2dehury.owlmail.data.remote.search.SearchResponse
import github.sachin2dehury.owlmail.data.remote.searchconv.SearchConvRequest
import github.sachin2dehury.owlmail.data.remote.searchconv.SearchConvResponse
import github.sachin2dehury.owlmail.data.remote.searchgal.SearchGalRequest
import github.sachin2dehury.owlmail.data.remote.searchgal.SearchGalResponse
import github.sachin2dehury.owlmail.data.remote.sendmsg.SendMsgRequest
import github.sachin2dehury.owlmail.data.remote.sendmsg.SendMsgResponse
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
        val reason: Reason? = null,
    ) : Parcelable {

        @JsonClass(generateAdapter = true)
        @Parcelize
        data class Reason(
            @Json(name = "Text")
            val text: String? = null,
        ) : Parcelable
    }
}
