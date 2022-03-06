package github.sachin2dehury.owlmail.data.remote.searchgal

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Attrs(
    val createTimeStamp: String? = null,
    val email: String? = null,
    val email2: String? = null,
    val fileAs: String? = null,
    val firstName: String? = null,
    val fullName: String? = null,
    val initials: String? = null,
    val lastName: String? = null,
    val modifyTimeStamp: String? = null,
    val objectClass: List<String>? = null,
    val zimbraId: String? = null,
) : Parcelable
