package com.emrizkis.zwallet.data.api

import com.emrizkis.zwallet.model.*
import com.emrizkis.zwallet.model.request.*
import com.emrizkis.zwallet.model.response.*
import retrofit2.Call
import retrofit2.http.*

interface ZWalletApi {
    @POST("auth/login")
//    suspend agar bejalan asynchronous
    suspend fun login(@Body request: LoginRequest): APIResponse<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): APIResponse<String>

    @GET("home/getBalance")
    suspend fun getBalance(): APIResponse<List<HomeUserResponse>>

    @POST("auth/refresh-token")
    fun refreshToken(@Body request: RefreshTokenRequest): Call<APIResponse<LoginResponse>>

    @GET("home/getInvoice")
    suspend fun getInvoice(): APIResponse<List<InvoiceResponse>>

    @GET("tranfer/contactUser")
    suspend fun getContacts(): APIResponse<List<ReceiverResponse>>

    @PATCH("auth/PIN")
    suspend fun setPin(@Body request: CreatePinRequest): APIResponse<String>

    @POST("tranfer/newTranfer")
    suspend fun transferAmount(@Body request: TransferRequest, @Header("x-access-PIN") pin:String): APIResponse<TransferResponse>

    @GET("/user/myProfile")
    suspend fun getProfilInfo(): APIResponse<ProfileResponse>



//    yg masuk ke viewmodel wajib ada suspend, karena asynchron
}
