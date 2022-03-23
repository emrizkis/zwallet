package com.emrizkis.zwallet.data.api

import com.emrizkis.zwallet.model.*
import com.emrizkis.zwallet.model.request.*
import com.emrizkis.zwallet.model.response.*
import retrofit2.Call
import retrofit2.http.*

interface ZWalletApi {

//    suspend agar bejalan asynchronous

//--------------    for auth process
    @POST("auth/login")

    suspend fun login(@Body request: LoginRequest): APIResponse<LoginResponse>

    @POST("auth/signup")
    suspend fun register(@Body request: RegisterRequest): APIResponse<String>

    @PATCH("auth/PIN")
    suspend fun setPin(@Body request: CreatePinRequest): APIResponse<String>

    @GET("auth/checkPIN/{pin}")
    suspend fun checkPin(@Path("pin") pin: String): APIResponse<String>

    @GET("auth/activate/{email}/{OTP}")
    suspend fun tokenActivation(@Path("email") email: String, @Path("OTP") OTP: String) : APIResponse<String>



//--------------

//--------------    for home activity
    @GET("home/getBalance")
    suspend fun getBalance(): APIResponse<List<HomeUserResponse>>

    @POST("auth/refresh-token")
    fun refreshToken(@Body request: RefreshTokenRequest): Call<APIResponse<LoginResponse>>

    @GET("home/getInvoice")
    suspend fun getInvoice(): APIResponse<List<InvoiceResponse>>
//--------------


//--------------     for transfer activity
    @GET("tranfer/contactUser")
    suspend fun getContacts(): APIResponse<List<ReceiverResponse>>

    @POST("tranfer/newTranfer")
    suspend fun transferAmount(@Body request: TransferRequest, @Header("x-access-PIN") pin:String): APIResponse<TransferResponse>
//--------------

//--------------     for topup activity
    @POST("topup/topupbalance")
    suspend fun topupBalance(@Body request: TopupRequest): APIResponse<String>


//--------------    for profile activity
    @GET("/user/myProfile")
    suspend fun getProfilInfo(): APIResponse<ProfileResponse>

    @PATCH("user/changePassword")
    suspend fun changePassword(@Body request: ChangePasswordRequest): APIResponse<String>

    @PATCH("user/changeInfo")
    suspend fun changeProfile(@Body request: EditProfileRequest): APIResponse<String>
//--------------



//    yg masuk ke viewmodel wajib ada suspend, karena asynchron
}
