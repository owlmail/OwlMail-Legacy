package github.sachin2dehury.owlmail.data.searchgal


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Contact(
    @Json(name = "_attrs")
    val attrs: Attrs? = null,
    @Json(name = "d")
    val time: Long? = null,
    val fileAsStr: String? = null,
    val id: String? = null,
    val l: String? = null,
    val ref: String? = null,
    val rev: Int? = null,
    val sf: String? = null
) : Parcelable