package github.sachin2dehury.owlmail.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.sachin2dehury.owlmail.data.remote.searchconv.Message

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: List<Message>?)

    @Query("SELECT * FROM message")
    fun getMessage(): PagingSource<Int, Message>

    @Query("DELETE FROM message")
    suspend fun deleteMessage()

    @Query("SELECT * FROM message WHERE (body LIKE '%' || :query || '%' OR subject LIKE '%' || :query || '%') AND folder LIKE :folder ORDER BY date DESC")
    fun searchMessage(query: String, folder: Int): PagingSource<Int, Message>
}
