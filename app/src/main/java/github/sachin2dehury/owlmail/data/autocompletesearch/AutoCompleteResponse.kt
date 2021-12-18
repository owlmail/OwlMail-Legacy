package github.sachin2dehury.owlmail.data.autocompletesearch


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AutoCompleteResponse(
    val match: List<Match>? = null
) : Parcelable