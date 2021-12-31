package github.sachin2dehury.owlmail.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ZimbraApiExt(
    private val moshi: Moshi,
    private val okHttpClient: OkHttpClient,
) {

    private var zimbraApi: ZimbraApi? = null

    fun provideMailApi(baseURL: String? = null): ZimbraApi = zimbraApi ?: Retrofit.Builder()
        .baseUrl(baseURL ?: "https://mail.nitrkl.ac.in/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
        .create(ZimbraApi::class.java).also { zimbraApi = it }

}