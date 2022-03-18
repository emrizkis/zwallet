package com.emrizkis.zwallet.data.api

import androidx.lifecycle.MutableLiveData
import com.emrizkis.zwallet.model.*
import com.emrizkis.zwallet.model.request.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ZWalletApi {
    @POST("auth/login")
//    suspend agar bejalan asynchronous
    suspend fun login(@Body request: LoginRequest): APIResponse<User>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): APIResponse<String>

    @GET("home/getBalance")
    suspend fun getBalance(): APIResponse<List<UserDetail>>

    @POST("auth/refresh-token")
    fun refreshToken(@Body request: RefreshTokenRequest): Call<APIResponse<User>>

    @GET("home/getInvoice")
    suspend fun getInvoice(): APIResponse<List<Invoice>>

    @GET("tranfer/contactUser")
    suspend fun getContacts(): APIResponse<List<ContactReceiver>>

    @POST("tranfer/auth/PIN")
    suspend fun setPin(@Body request: PinRequest): APIResponse<String>

    @POST("tranfer/newTranfer")
    suspend fun transferAmount(@Body request: MutableLiveData<TransferRequest>): APIResponse<TransferResponse>


//    yg masuk ke viewmodel wajib ada suspend, karena asynchron
}
