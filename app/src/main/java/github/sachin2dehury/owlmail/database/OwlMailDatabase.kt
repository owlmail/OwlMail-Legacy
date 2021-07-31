package github.sachin2dehury.owlmail.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import github.sachin2dehury.owlmail.datamodel.Mail

@Database(
    entities = [Mail::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class OwlMailDatabase : RoomDatabase() {
    abstract fun getMailDao(): MailDao
}