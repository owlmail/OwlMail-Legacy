package github.sachin2dehury.owlmail.data.remote.searchgal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "contact")
@JsonClass(generateAdapter = true)
@Parcelize
data class Contact(
    @Json(name = "_attrs")
    val attrs: Attrs? = null,
    @Json(name = "d")
    val time: Long? = null,
    val fileAsStr: String? = null,
    @PrimaryKey
    val id: String = "",
    val l: String? = null,
    val ref: String? = null,
    val rev: Int? = null,
    val sf: String? = null,
) : Parcelable
