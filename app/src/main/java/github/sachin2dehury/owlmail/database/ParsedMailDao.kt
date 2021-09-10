package github.sachin2dehury.owlmail.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.sachin2dehury.owlmail.datamodel.ParsedMail

@Dao
interface ParsedMailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMail(parsedMail: ParsedMail)

    @Query("SELECT * FROM parsed_mails WHERE conversationId = :conversationId ORDER BY time DESC")
    suspend fun getConversationMails(conversationId: Int): List<ParsedMail>

    @Query("DELETE FROM parsed_mails")
    suspend fun deleteAllMails()
}