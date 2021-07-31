package github.sachin2dehury.owlmail.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.database.Converters
import github.sachin2dehury.owlmail.database.OwlMailDatabase

@Module
@InstallIn(ActivityRetainedComponent::class)
object DatabaseModule {

    @ActivityRetainedScoped
    @Provides
    fun provideConverters(moshi: Moshi) = Converters(moshi)

    @ActivityRetainedScoped
    @Provides
    fun provideMailDatabase(
        @ApplicationContext context: Context,
        converters: Converters
    ) = Room.databaseBuilder(
        context,
        OwlMailDatabase::class.java,
        context.getString(R.string.database_name)
    ).addTypeConverter(converters).fallbackToDestructiveMigration().build()

    @ActivityRetainedScoped
    @Provides
    fun provideMailDao(mailDatabase: OwlMailDatabase) = mailDatabase.getMailDao()
}