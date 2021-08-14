package github.sachin2dehury.owlmail.api

import github.sachin2dehury.owlmail.datamodel.Items
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MailApi {

    @GET(HOME_URL + AUTH_SET_COOKIE + JSON_FORMAT + ITEM_QUERY_NONE)
    suspend fun attemptLogin(): Response<Items>

    @GET(HOME_URL + AUTH_SET_COOKIE + JSON_FORMAT + ITEM_QUERY_ALL)
    suspend fun getMails(
        @Query("query") month: String,
    ): Response<Items>

    @GET(MOBILE_URL + ACTION_VIEW + LOAD_IMAGES)
    suspend fun getParsedMail(
        @Query("id") id: Int,
    ): ResponseBody

//    @GET(HTML_URL + AUTH_FROM_COOKIE + LOAD_IMAGES)
//    suspend fun getParsedMail(
//        @Query("id") id: Int,
//    ): ResponseBody

    @GET(HOME_URL + AUTH_FROM_COOKIE + JSON_FORMAT)
    suspend fun searchMails(
        @Query("query") query: String,
    ): Response<Items>
}