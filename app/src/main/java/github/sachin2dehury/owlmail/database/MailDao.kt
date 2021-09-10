package github.sachin2dehury.owlmail.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.sachin2dehury.owlmail.datamodel.Mail

@Dao
interface MailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMails(mails: List<Mail>)

    @Query("SELECT * FROM mails WHERE time >= :startTime AND time < :endTime AND box = :box ORDER BY time DESC")
    suspend fun getMails(box: Byte, startTime: Long, endTime: Long): List<Mail>

    @Query("SELECT id FROM mails WHERE conversationId = :conversationId ORDER BY id DESC")
    suspend fun getMailsId(conversationId: Int): List<Int>

    @Query("DELETE FROM mails")
    suspend fun deleteAllMails()

    @Query("DELETE FROM mails WHERE time >= :startTime AND time < :endTime AND box = :box")
    suspend fun deleteMails(box: Byte, startTime: Long, endTime: Long)

//    @Query("UPDATE mails SET flag = REPLACE(flag,'u','') WHERE id = :id")
//    suspend fun markAsRead(id: Int)

    @Query("SELECT * FROM mails WHERE body LIKE '%' || :query || '%' OR subject LIKE '%' || :query || '%' ORDER BY time DESC")
    suspend fun searchMails(query: String): List<Mail>
}