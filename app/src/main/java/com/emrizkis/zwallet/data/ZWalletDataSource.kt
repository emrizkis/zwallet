package com.emrizkis.zwallet.data

import androidx.lifecycle.liveData
import com.emrizkis.zwallet.data.api.ZWalletApi
import com.emrizkis.zwallet.model.request.LoginRequest
import com.emrizkis.zwallet.model.request.PinRequest
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

    fun getInvoice() = liveData(Dispatchers.IO){
        emit(Resource.loading( null))
        try {
            val response = apiClient.getInvoice()
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

    fun getContact() = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            val response = apiClient.getContacts()
            emit(Resource.success(response))
        } catch (e: java.lang.Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }

   fun addPin(pin: String) = liveData(Dispatchers.IO){
       emit(Resource.loading(null))
       try {
           val response = apiClient.addPin(PinRequest(pin))
           emit(Resource.success(response))
       } catch (e: Exception){
           emit(Resource.error(null, e.localizedMessage))

       }
   }

}