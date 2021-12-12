package github.sachin2dehury.owlmail.database

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import github.sachin2dehury.owlmail.datamodel.Address
import github.sachin2dehury.owlmail.datamodel.Inv
import javax.inject.Inject

class Converters {

    @Inject
    lateinit var moshi: Moshi
//    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val invListType = listOf<Inv>().javaClass
    private val stringListType = listOf<String>().javaClass
    private val addressListType = listOf<Address>().javaClass

    @TypeConverter
    @ToJson
    fun invToJson(value: List<Inv>?): String = moshi.adapter(invListType).toJson(value)

    @TypeConverter
    @FromJson
    fun invFromJson(value: String) = moshi.adapter(invListType).fromJson(value)

    @TypeConverter
    @ToJson
    fun stringToJson(value: List<String>?): String = moshi.adapter(stringListType).toJson(value)

    @TypeConverter
    @FromJson
    fun stringFromJson(value: String) = moshi.adapter(stringListType).fromJson(value)

    @TypeConverter
    @ToJson
    fun toJson(value: List<Address>?): String = moshi.adapter(addressListType).toJson(value)

    @TypeConverter
    @FromJson
    fun fromJson(value: String) = moshi.adapter(addressListType).fromJson(value)

}
