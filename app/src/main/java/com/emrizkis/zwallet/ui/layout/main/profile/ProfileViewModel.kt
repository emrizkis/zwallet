package com.emrizkis.zwallet.ui.layout.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emrizkis.zwallet.data.ZWalletDataSource
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.request.ChangePasswordRequest
import com.emrizkis.zwallet.model.request.EditProfileRequest
import com.emrizkis.zwallet.model.response.ProfileResponse
import com.emrizkis.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel


class ProfileViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel() {

    private var pin = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var firstName = MutableLiveData<String>()
    var lastName = MutableLiveData<String>()
    var verifiedEmail = MutableLiveData<String>()
    var phone = MutableLiveData<String>()
//    val username = MutableLiveData<String>()


    val balance = MutableLiveData<String>()

    fun getProfileInfo(): LiveData<Resource<APIResponse<ProfileResponse>?>> {
        return dataSource.getProfileInfo()
    }

    fun checkPin(getpin: String): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.checkPin(getpin)
    }

    fun setPin(data: String){
        pin.value = data
    }

    fun getPin(): MutableLiveData<String>{
        return pin
    }

    fun createPin(data: String) : LiveData<Resource<APIResponse<String>?>> {
        return dataSource.setPin(data)
    }

    fun changePassword(data: ChangePasswordRequest): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.changePasswordRequest(data)
    }

    fun changeProfile(data: EditProfileRequest): LiveData<Resource<APIResponse<ProfileResponse>?>> {
        return dataSource.changeProfle(data)
    }


}