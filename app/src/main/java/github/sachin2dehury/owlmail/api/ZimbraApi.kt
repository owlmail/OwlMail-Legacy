package github.sachin2dehury.owlmail.api

import github.sachin2dehury.owlmail.data.remote.ZimbraSoap
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ZimbraApi {

    @POST("service/soap/AuthRequest")
    suspend fun makeAuthRequest(@Body body: ZimbraSoap): Response<ZimbraSoap>

    @POST("service/soap/AutoCompleteRequest")
    suspend fun makeAutoCompleteRequest(@Body body: ZimbraSoap): Response<ZimbraSoap>

    @POST("service/soap/SearchRequest")
    suspend fun makeSearchRequest(@Body body: ZimbraSoap): Response<ZimbraSoap>

    @POST("service/soap/SearchGalRequest")
    suspend fun makeSearchGalRequest(@Body body: ZimbraSoap): Response<ZimbraSoap>

    @POST("service/soap/SearchConvRequest")
    suspend fun makeSearchConvRequest(@Body body: ZimbraSoap): Response<ZimbraSoap>

    @POST("service/soap/GetMsgRequest")
    suspend fun makeGetMsgRequest(@Body body: ZimbraSoap): Response<ZimbraSoap>

    @POST("service/soap/ConvActionRequest")
    suspend fun makeConvActionRequest(@Body body: ZimbraSoap): Response<ZimbraSoap>

    @POST("service/soap/SaveDraftRequest")
    suspend fun makeSaveDraftRequest(@Body body: ZimbraSoap): Response<ZimbraSoap>

    @POST("service/soap/GetContactsRequest")
    suspend fun makeGetContactsRequest(@Body body: ZimbraSoap): Response<ZimbraSoap>

    @POST("service/soap/SendMsgRequest")
    suspend fun makeSendMsgRequest(@Body body: ZimbraSoap): Response<ZimbraSoap>
}
