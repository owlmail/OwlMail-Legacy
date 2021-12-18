package github.sachin2dehury.owlmail.data.search


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchResponse(
    @Json(name = "c")
    val conversations: List<Conversation>? = null,
    @Json(name = "more")
    val hasMore: Boolean? = null,
) : Parcelable