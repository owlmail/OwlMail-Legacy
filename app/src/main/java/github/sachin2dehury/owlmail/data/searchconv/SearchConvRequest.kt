package github.sachin2dehury.owlmail.data.searchconv

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.constants.ZimbraFetch
import github.sachin2dehury.owlmail.data.constants.ZimbraRecipients
import github.sachin2dehury.owlmail.data.constants.ZimbraSoapJson
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchConvRequest(
    val cid: String? = null,
    val fetch: String? = ZimbraFetch.ALL.value,
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.MAIL.value,
    val limit: Int? = null,
    val offset: Int? = null,
    val recip: String? = ZimbraRecipients.ALL.value,
) : Parcelable

// TODO check what is this
// "fetch": "u!",
// "recip": "2"
