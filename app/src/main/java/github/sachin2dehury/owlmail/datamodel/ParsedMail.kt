package github.sachin2dehury.owlmail.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import org.jsoup.nodes.Document

@Entity(tableName = "parsed_mails")
@JsonClass(generateAdapter = true)
data class ParsedMail(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val conversationId: Int? = null,
    val time: Long? = null,
    val from: String? = null,
    val subject: String? = null,
    val address: List<String>? = null,
    val body: String? = null,
    val attachments: String? = null,
) {

    constructor(
        id: Int,
        conversationId: Int?,
        mail: Document,
    ) : this(
        id,
        conversationId,
        mail.select(".small-gray-text").text().toLongOrNull(),
        mail.select("#d_from").text(),
        mail.select(".zo_unread").text(),
        mail.select("#d_div .View.address").eachText(),
        mail.select(".msgwrap").toString(),
        mail.select(".View.attachments").toString()
    )
}