package github.sachin2dehury.owlmail.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PagingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(databasePagingKey: DatabasePagingKey)

    @Query("SELECT * FROM paging_keys WHERE `key` = :key")
    fun getKey(key: Int): Flow<DatabasePagingKey>

    @Query("DELETE FROM paging_keys WHERE `key`= :key")
    suspend fun deleteKey(key: Int)

    @Query("DELETE FROM paging_keys")
    suspend fun deleteAllKeys()
}