package github.sachin2dehury.owlmail.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "paging_keys")
@JsonClass(generateAdapter = true)
data class DatabasePagingKey(
    @PrimaryKey(autoGenerate = false)
    val key: Int = 0,
    val values: List<Int>,
)
