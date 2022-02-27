package github.sachin2dehury.owlmail.data.search

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.common.Email
import kotlinx.parcelize.Parcelize

@Entity(tableName = "conversation")
@JsonClass(generateAdapter = true)
@Parcelize
data class Conversation(
    @Json(name = "d")
    val date: Long? = null,
    @Json(name = "e")
    val emails: List<Email>? = null,
    @Json(name = "f")
    val flags: String? = null,
    @Json(name = "fr")
    val body: String? = null,
    @PrimaryKey
    val id: String = "",
    @Json(name = "m")
    val msgMetas: List<MsgMeta>? = null,
    @Json(name = "n")
    val n: Int? = null,
    @Json(name = "sf")
    val sf: String? = null,
    @Json(name = "su")
    val subject: String? = null,
    @Json(name = "u")
    val u: Int? = null
) : Parcelable
