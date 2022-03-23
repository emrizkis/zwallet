package com.emrizkis.zwallet.ui.layout.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emrizkis.zwallet.data.ZWalletDataSource
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.response.LoginResponse
import com.emrizkis.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel() {
//    private var apiClient: ZWalletApi = NetworkConfig(app).buildApi()
//    private var dataSource = ZWalletDataSource(apiClient)

    private var pin = MutableLiveData<String>()
    private var email = MutableLiveData<String>()

    fun login(email: String, password: String) : LiveData<Resource<APIResponse<LoginResponse>?>> {
        return dataSource.login(email,password)
    }

    fun registerAccount(username: String, email: String, password: String) : LiveData<Resource<APIResponse<String>?>> {
        return dataSource.register(username = username, email = email, password = password)
    }

    fun tokenActivation(email: String, OTP: String): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.tokenActivation(email=email, OTP = OTP)
    }

    fun createPin(data: String) : LiveData<Resource<APIResponse<String>?>> {
        return dataSource.setPin(data)
    }

    fun setPin(data: String){
        pin.value = data
    }

    fun getPin(): MutableLiveData<String>{
        return pin
    }

    fun setEmail(data: String){
        email.value = data
    }

    fun getEmail(): MutableLiveData<String>{
        return email
    }

}