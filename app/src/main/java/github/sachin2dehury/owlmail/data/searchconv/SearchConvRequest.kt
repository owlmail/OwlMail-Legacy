package github.sachin2dehury.owlmail.data.searchconv


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.api.ZimbraSoapJson
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchConvRequest(
    val cid: String? = null,
    val fetch: String? = "u!",
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.Mail.value,
    val limit: Int? = null,
    val offset: Int? = null,
    val recip: String? = "2"
) : Parcelable

//TODO check what is this
//"fetch": "u!",
//"recip": "2"