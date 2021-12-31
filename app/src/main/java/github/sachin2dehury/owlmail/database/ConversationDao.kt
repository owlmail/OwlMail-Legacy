package github.sachin2dehury.owlmail.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.sachin2dehury.owlmail.data.search.Conversation

@Dao
interface ConversationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversation(conversations: List<Conversation>)

    @Query("SELECT * FROM conversation")
    suspend fun getConversation(): PagingSource<Int, Conversation>

    @Query("DELETE FROM conversation")
    suspend fun deleteConversation()

    @Query("SELECT * FROM conversation WHERE body LIKE '%' || :query || '%' OR subject LIKE '%' || :query || '%' ORDER BY date DESC")
    suspend fun searchConversation(query: String): PagingSource<Int, Conversation>
}