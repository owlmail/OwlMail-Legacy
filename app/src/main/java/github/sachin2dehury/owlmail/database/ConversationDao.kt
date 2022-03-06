package github.sachin2dehury.owlmail.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.sachin2dehury.owlmail.data.remote.search.Conversation

@Dao
interface ConversationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversation(conversations: List<Conversation>?)

    @Query("SELECT * FROM conversation")
    fun getConversation(): PagingSource<Int, Conversation>

    @Query("DELETE FROM conversation")
    suspend fun deleteConversation()

    @Query("SELECT * FROM conversation WHERE (body LIKE '%' || :query || '%' OR subject LIKE '%' || :query || '%') OR u LIKE :folder ORDER BY date DESC")
    fun searchConversation(query: String, folder: Int): PagingSource<Int, Conversation>
}
