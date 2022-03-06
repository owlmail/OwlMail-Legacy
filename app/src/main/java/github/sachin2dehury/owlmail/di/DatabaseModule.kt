package github.sachin2dehury.owlmail.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.database.Converters
import github.sachin2dehury.owlmail.database.OwlMailDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesConverters(moshi: Moshi) = Converters(moshi)

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context,
        converters: Converters,
    ) = Room.databaseBuilder(
        context,
        OwlMailDatabase::class.java,
        context.getString(R.string.database_name)
    ).addTypeConverter(converters).build()

    @Singleton
    @Provides
    fun provideContactDao(database: OwlMailDatabase) = database.getContactDao()

    @Singleton
    @Provides
    fun provideConversationDao(database: OwlMailDatabase) = database.getConversationDao()

    @Singleton
    @Provides
    fun provideMessageDao(database: OwlMailDatabase) = database.getMessageDao()
}
