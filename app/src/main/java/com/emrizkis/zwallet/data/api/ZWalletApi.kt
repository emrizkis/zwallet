package com.emrizkis.zwallet.data.api

import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.Invoice
import com.emrizkis.zwallet.model.User
import com.emrizkis.zwallet.model.UserDetail
import com.emrizkis.zwallet.model.request.LoginRequest
import com.emrizkis.zwallet.model.request.RefreshTokenRequest
import com.emrizkis.zwallet.model.request.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ZWalletApi {
    @POST("auth/login")
//    suspend agar bejalan asynchronous
    suspend fun login(@Body request: LoginRequest): APIResponse<User>

    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Call<APIResponse<String>>

    @GET("home/getBalance")
    suspend fun getBalance(): APIResponse<List<UserDetail>>

    @POST("auth/refresh-token")
    fun refreshToken(@Body request: RefreshTokenRequest): Call<APIResponse<User>>

    @GET("home/getInvoice")
    suspend fun getInvoice(): APIResponse<List<Invoice>>

//    yg masuk ke viewmodel wajib ada suspend, karena asynchron
}
