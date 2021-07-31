package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Inv(
    @Json(name = "comp")
    val comp: List<Comp>?,
    @Json(name = "compNum")
    val compNum: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "seq")
    val seq: Int?,
    @Json(name = "type")
    val type: String?
)