package github.sachin2dehury.owlmail.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "parsed_mails")
@JsonClass(generateAdapter = true)
data class ParsedMail(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val conversationId: Int? = null,
    val time: Long? = null,
    val details: List<String>? = null,
    val body: String? = null,
    val noOfAttachments: Int? = null,
    val attachments: List<String>? = null,
)