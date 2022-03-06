package github.sachin2dehury.owlmail.data.remote.search

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.enums.ZimbraSearchType
import github.sachin2dehury.owlmail.data.enums.ZimbraSoapJson
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchRequest(
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.MAIL.value,
    val limit: Int? = null,
    val offset: Int? = null,
    val query: String? = null,
    val types: String? = ZimbraSearchType.CONVERSATION.value,
) : Parcelable