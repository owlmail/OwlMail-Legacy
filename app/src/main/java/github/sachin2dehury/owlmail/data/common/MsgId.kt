package github.sachin2dehury.owlmail.data.common

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class MsgId(
    val id: String? = null
) : Parcelable
