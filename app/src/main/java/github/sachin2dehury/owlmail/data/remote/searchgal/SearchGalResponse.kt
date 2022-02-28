package github.sachin2dehury.owlmail.data.remote.searchgal

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchGalResponse(
    @Json(name = "cn")
    val contact: List<Contact>? = null,
    @Json(name = "more")
    val hasMore: Boolean? = null,
) : Parcelable
