package github.sachin2dehury.owlmail.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import github.sachin2dehury.owlmail.datamodel.Address
import github.sachin2dehury.owlmail.datamodel.Inv

class Converters {

    @TypeConverter
    fun fromAddressList(value: List<Address>?): String =
        Moshi.Builder().build().adapter<List<Address>>(
            Types.newParameterizedType(
                List::class.java,
                Address::class.java
            )
        ).toJson(value)

    @TypeConverter
    fun toAddressList(value: String): List<Address>? =
        Moshi.Builder().build().adapter<List<Address>>(
            Types.newParameterizedType(
                List::class.java,
                Address::class.java
            )
        ).fromJson(value)

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
}
