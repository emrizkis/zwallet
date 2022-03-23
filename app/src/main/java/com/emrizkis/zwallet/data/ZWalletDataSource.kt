package com.emrizkis.zwallet.data

import androidx.lifecycle.liveData
import com.emrizkis.zwallet.data.api.ZWalletApi
import com.emrizkis.zwallet.model.request.*
import com.emrizkis.zwallet.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ZWalletDataSource @Inject constructor(private val apiClient: ZWalletApi){

    fun login(email: String, password: String) = liveData(Dispatchers.IO){
//        ZWalletDataSource->login()
//        return State.LOADING
//        try-> catch
//        return State.APIResponse

        emit(Resource.loading( null))
        try {
            val loginRequest = LoginRequest(email = email, password = password)
            val response = apiClient.login(loginRequest)
            emit(Resource.success(response))
        } catch (e: Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }


    fun register(username: String, email: String, password: String) = liveData(Dispatchers.IO){
        emit(Resource.loading( null))
        try {
            val registerRequest =RegisterRequest(username = username, email = email, password = password)
            val response = apiClient.register(registerRequest)
            emit(Resource.success(response))
        } catch (e: Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun setPin(pin: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response = apiClient.setPin(CreatePinRequest(pin))
            emit(Resource.success(response))
        } catch (e: Exception){
            emit(Resource.error(null, e.localizedMessage))

        }
    }

    fun getBalance() = liveData(Dispatchers.IO){
        emit(Resource.loading( null))
        try {
            val response = apiClient.getBalance()
            emit(Resource.success(response))
        } catch (e: Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun getInvoice() = liveData(Dispatchers.IO){
        emit(Resource.loading( null))
        try {
            val response = apiClient.getInvoice()
            emit(Resource.success(response))
        } catch (e: Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun getContact() = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response = apiClient.getContacts()
            emit(Resource.success(response))
        } catch (e: java.lang.Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun transferAmount(transferRequest: TransferRequest, pin: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
//            val transferRequest = TransferRequest(receiver = receiver, amount = amount, notes = notes)
            val response = apiClient.transferAmount(transferRequest, pin)
            println(response.toString())
            emit(Resource.success(response))
        } catch (e: java.lang.Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun getProfileInfo() = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response = apiClient.getProfilInfo()
            emit(Resource.success(response))

        }catch (e: java.lang.Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun checkPin(getpin: String) = liveData(Dispatchers.IO)  {
        emit(Resource.loading(null))
        try{
            val response = apiClient.checkPin(getpin)
            emit(Resource.success(response))
        } catch (e: java.lang.Exception){
            emit(Resource.error(null, e.localizedMessage))
        }

    }

    fun changePasswordRequest(pass: ChangePasswordRequest) = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try{
            val response = apiClient.changePassword(pass)
            emit(Resource.success(response))
        } catch (e: java.lang.Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun tokenActivation(email: String, OTP: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response = apiClient.tokenActivation(email = email, OTP = OTP)
            emit(Resource.success(response))
        } catch (e: java.lang.Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun topupBalance(phone: String, amount: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response = apiClient.topupBalance(
                TopupRequest(
                    phone = phone,
                    amount = amount
                )
            )
            emit(Resource.success(response))
        } catch (e: java.lang.Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun changeProfle(request: EditProfileRequest) = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response = apiClient.changeProfile(request)
            emit(Resource.success(response))
        } catch (e: java.lang.Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }


}