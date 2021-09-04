package github.sachin2dehury.owlmail.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import github.sachin2dehury.owlmail.datamodel.Address
import github.sachin2dehury.owlmail.datamodel.Inv

class Converters {

    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @TypeConverter
    fun fromListInv(value: List<Inv>): String = Moshi.Builder().build().adapter<List<Inv>>(
        Types.newParameterizedType(
            List::class.java,
            Inv::class.java
        )
    ).toJson(value)

    @TypeConverter
    fun toListInv(value: String): List<Inv>? = Moshi.Builder().build().adapter<List<Inv>>(
        Types.newParameterizedType(
            List::class.java,
            Inv::class.java
        )
    ).fromJson(value)

    @TypeConverter
    fun fromListString(value: List<String>): String = Moshi.Builder().build().adapter<List<String>>(
        Types.newParameterizedType(
            List::class.java,
            String::class.java
        )
    ).toJson(value)

    @TypeConverter
    fun toListString(value: String): List<String>? = Moshi.Builder().build().adapter<List<String>>(
        Types.newParameterizedType(
            List::class.java,
            String::class.java
        )
    ).fromJson(value)

    @TypeConverter
    fun fromListInt(value: List<Int>): String = Moshi.Builder().build().adapter<List<Int>>(
        Types.newParameterizedType(
            List::class.java,
            Int::class.java
        )
    ).toJson(value)

    @TypeConverter
    fun toListInt(value: String): List<Int>? = Moshi.Builder().build().adapter<List<Int>>(
        Types.newParameterizedType(
            List::class.java,
            Int::class.java
        )
    ).fromJson(value)

    @TypeConverter
    fun fromListAddress(value: List<Address>): String =
        Moshi.Builder().build().adapter<List<Address>>(
            Types.newParameterizedType(
                List::class.java,
                Address::class.java
            )
        ).toJson(value)

    @TypeConverter
    fun toListAddress(value: String): List<Address>? =
        Moshi.Builder().build().adapter<List<Address>>(
            Types.newParameterizedType(
                List::class.java,
                Address::class.java
            )
        ).fromJson(value)
}
