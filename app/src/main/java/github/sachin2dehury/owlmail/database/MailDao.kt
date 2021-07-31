package github.sachin2dehury.owlmail.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.sachin2dehury.owlmail.datamodel.Mail

@Dao
interface MailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMails(mails: List<Mail>)

    @Query("SELECT * FROM mails WHERE box = :box ORDER BY time DESC")
    fun getMails(box: String): PagingSource<Int, Mail>

    @Query("SELECT id FROM mails WHERE conversationId = :conversationId ORDER BY id DESC")
    fun getMailsId(conversationId: String): PagingSource<Int, String>

    @Query("DELETE FROM mails")
    suspend fun deleteAllMails()

//    @Query("UPDATE mails SET flag = REPLACE(flag,'u','') WHERE id = :id")
//    suspend fun markAsRead(id: Int)

    @Query("SELECT * FROM mails WHERE body LIKE '%' || :query || '%' OR subject LIKE '%' || :query || '%' ORDER BY time DESC")
    fun searchMails(query: String): PagingSource<Int, Mail>
}