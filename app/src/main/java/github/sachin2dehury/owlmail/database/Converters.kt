package github.sachin2dehury.owlmail.database

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import javax.inject.Inject

class Converters {

    @Inject
    lateinit var moshi: Moshi

    private val stringType = listOf<String>().javaClass

    @TypeConverter
    @ToJson
    fun stringToJson(value: List<String>?): String = moshi.adapter(stringType).toJson(value)

    @TypeConverter
    @FromJson
    fun stringFromJson(value: String) = moshi.adapter(stringType).fromJson(value)
}
