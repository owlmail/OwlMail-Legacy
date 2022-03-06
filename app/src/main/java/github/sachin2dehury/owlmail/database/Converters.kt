package github.sachin2dehury.owlmail.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import github.sachin2dehury.owlmail.data.remote.common.Email
import github.sachin2dehury.owlmail.data.remote.common.MultiPartMsg
import github.sachin2dehury.owlmail.data.remote.search.MsgMeta
import github.sachin2dehury.owlmail.data.remote.searchgal.Attrs

@ProvidedTypeConverter
class Converters(private val moshi: Moshi) {

    private val emailListType = listOf<Email>().javaClass
    private val msgMetaListType = listOf<MsgMeta>().javaClass
    private val multiPartMsgListType = listOf<MultiPartMsg>().javaClass
    private val attrsType = Attrs().javaClass

    @TypeConverter
    @ToJson
    fun attrsToJson(value: Attrs?): String = moshi.adapter(attrsType).toJson(value)

    @TypeConverter
    @FromJson
    fun attrsFromJson(value: String) = moshi.adapter(attrsType).fromJson(value)

    @TypeConverter
    @ToJson
    fun emailListToJson(value: List<Email>?): String = moshi.adapter(emailListType).toJson(value)

    @TypeConverter
    @FromJson
    fun emailListFromJson(value: String) = moshi.adapter(emailListType).fromJson(value)

    @TypeConverter
    @ToJson
    fun msgMetaListToJson(value: List<MsgMeta>?): String =
        moshi.adapter(msgMetaListType).toJson(value)

    @TypeConverter
    @FromJson
    fun msgMetaListFromJson(value: String) = moshi.adapter(msgMetaListType).fromJson(value)

    @TypeConverter
    @ToJson
    fun multiPartMsgListToJson(value: List<MultiPartMsg>?): String =
        moshi.adapter(multiPartMsgListType).toJson(value)

    @TypeConverter
    @FromJson
    fun multiPartMsgListFromJson(value: String) =
        moshi.adapter(multiPartMsgListType).fromJson(value)
}
