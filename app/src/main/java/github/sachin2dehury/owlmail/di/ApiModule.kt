package github.sachin2dehury.owlmail.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.sachin2dehury.owlmail.api.BasicAuthInterceptor
import github.sachin2dehury.owlmail.api.MailApiExt
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideBasicAuthInterceptor() = BasicAuthInterceptor()

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context) =
        ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        basicAuthInterceptor: BasicAuthInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(basicAuthInterceptor)
        .addInterceptor(chuckerInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideMailApi(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): MailApiExt = MailApiExt(moshi, okHttpClient)
}