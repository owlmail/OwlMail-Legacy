package github.sachin2dehury.owlmail.data.search


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.common.ZimbraSoapJson
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchRequest(
    @Json(name = "_jsns")
    val jsns: String = ZimbraSoapJson.Mail.value,
    val limit: Int? = null,
    val offset: Int? = null,
    val query: String? = null,
    val types: String? = null
) : Parcelable