package com.emrizkis.zwallet.data.api

import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.User
import com.emrizkis.zwallet.model.UserDetail
import com.emrizkis.zwallet.model.request.LoginRequest
import com.emrizkis.zwallet.model.request.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ZWalletApi {
    @POST("auth/login")
//    suspend agar bejalan asynchronous
    fun login(@Body request: LoginRequest): Call<APIResponse<User>>

    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Call<APIResponse<String>>

    @GET("home/getBalance")
    fun getBalance(): Call<APIResponse<List<UserDetail>>>
}
