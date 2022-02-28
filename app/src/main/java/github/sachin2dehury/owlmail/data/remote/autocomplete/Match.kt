package github.sachin2dehury.owlmail.data.remote.autocomplete

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Match(
    val email: String? = null,
    val isGroup: Boolean? = null,
    val ranking: String? = null,
    val type: String? = null,
) : Parcelable
