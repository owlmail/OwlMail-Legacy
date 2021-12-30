package github.sachin2dehury.owlmail.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MailApiExt(
    private val moshi: Moshi,
    private val okHttpClient: OkHttpClient,
) {

    private var mailApi: MailApi? = null

    fun provideMailApi(baseURL: String? = null): MailApi = mailApi ?: Retrofit.Builder()
        .baseUrl(baseURL ?: "https://mail.nitrkl.ac.in/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
        .create(MailApi::class.java).also { mailApi = it }

}