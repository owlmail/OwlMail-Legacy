package github.sachin2dehury.owlmail.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.database.OwlMailDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMailDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        OwlMailDatabase::class.java,
        context.getString(R.string.database_name)
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMailDao(mailDatabase: OwlMailDatabase) = mailDatabase.getMailDao()

    @Singleton
    @Provides
    fun provideParsedMailDao(mailDatabase: OwlMailDatabase) = mailDatabase.getParsedMailDao()

    @Singleton
    @Provides
    fun providePagingDao(mailDatabase: OwlMailDatabase) = mailDatabase.getPagingDao()
}