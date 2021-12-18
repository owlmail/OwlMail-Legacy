package github.sachin2dehury.owlmail.data.searchconv


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchConvResponse(
    @Json(name = "m")
    val message: List<Message>? = null,
    @Json(name = "more")
    val hasMore: Boolean? = null,
) : Parcelable