package github.sachin2dehury.owlmail.datamodel


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "mails")
@JsonClass(generateAdapter = true)
data class Mail(
    @Json(name = "cid")
    val conversationId: Int = 0,
    @Json(name = "d")
    val time: Long = 0,
    @Json(name = "e")
    val addressList: List<Address>? = null,
    @Json(name = "f")
    val flags: String? = null,
    @Json(name = "fr")
    val body: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val inv: List<Inv>? = null,
    @Json(name = "l")
    val box: Byte = 0,
    val rev: Int = 0,
    @Json(name = "s")
    val size: Int = 0,
    @Json(name = "su")
    val subject: String? = null
) {
    constructor(
        id: String,
        conversationId: String,
        time: Long,
        addressList: List<Address>?,
        flags: String?,
        body: String,
        box: String,
        rev: Int,
        size: Int,
        subject: String,
    ) : this(
        id = id.toInt(),
        conversationId = conversationId.toInt(),
        time = time,
        addressList = addressList,
        flags = flags,
        body = if (body.isEmpty()) null else body.removeSuffix("..."),
        box = box.toByte(),
        rev = rev,
        size = size,
        subject = if (subject.isEmpty()) null else subject,
    )
}