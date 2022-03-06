package github.sachin2dehury.owlmail.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.sachin2dehury.owlmail.data.remote.searchgal.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(conversations: List<Contact>?)

    @Query("SELECT * FROM contact")
    fun getContact(): PagingSource<Int, Contact>

    @Query("DELETE FROM contact")
    suspend fun deleteContact()

    @Query("SELECT * FROM contact WHERE attrs LIKE '%' || :query || '%' OR attrs LIKE '%' || :query || '%' ORDER BY time DESC")
    fun searchContact(query: String): PagingSource<Int, Contact>
}
