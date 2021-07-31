package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Inv(
    @Json(name = "comp")
    val comp: List<Comp>? = null,
    @Json(name = "compNum")
    val compNum: Int? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "seq")
    val seq: Int? = null,
    @Json(name = "type")
    val type: String? = null
)