package github.sachin2dehury.owlmail.database

import androidx.room.Database
import androidx.room.RoomDatabase
import github.sachin2dehury.owlmail.datamodel.Mail

@Database(
    entities = [Mail::class],
    version = 1,
    exportSchema = false
)

abstract class OwlMailDatabase : RoomDatabase() {
    abstract fun getMailDao(): MailDao
}