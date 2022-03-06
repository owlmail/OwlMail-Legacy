package github.sachin2dehury.owlmail.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import github.sachin2dehury.owlmail.data.remote.search.Conversation
import github.sachin2dehury.owlmail.data.remote.searchconv.Message
import github.sachin2dehury.owlmail.data.remote.searchgal.Contact

@Database(
    entities = [Contact::class, Conversation::class, Message::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class OwlMailDatabase : RoomDatabase() {
    abstract fun getContactDao(): ContactDao
    abstract fun getConversationDao(): ConversationDao
    abstract fun getMessageDao(): MessageDao
}
