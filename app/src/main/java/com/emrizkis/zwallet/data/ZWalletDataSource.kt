package com.emrizkis.zwallet.data

import androidx.lifecycle.liveData
import com.emrizkis.zwallet.data.api.ZWalletApi
import com.emrizkis.zwallet.model.request.LoginRequest
import com.emrizkis.zwallet.model.request.PinRequest
import com.emrizkis.zwallet.model.request.RegisterRequest
import com.emrizkis.zwallet.model.request.TransferRequest
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
            val response = apiClient.setPin(PinRequest(pin))
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

    fun transferAmount(transferRequest: TransferRequest?) = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
//            val transferRequest = TransferRequest(receiver = receiver, amount = amount, notes = notes)
            val response = apiClient.transferAmount(transferRequest)
            emit(Resource.success(response))
        } catch (e: java.lang.Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }


}