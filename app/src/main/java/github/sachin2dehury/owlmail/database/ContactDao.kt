package github.sachin2dehury.owlmail.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.sachin2dehury.owlmail.data.searchgal.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(conversations: List<Contact>)

    @Query("SELECT * FROM contact")
    suspend fun getContact(): PagingSource<Int, Contact>

    @Query("DELETE FROM contact")
    suspend fun deleteContact()

//    @Query("SELECT * FROM contact WHERE attrs LIKE '%' || :query || '%' OR subject LIKE '%' || :query || '%' ORDER BY date DESC")
//    suspend fun searchContact(query: String): PagingSource<Int, Contact>
}
