package com.emrizkis.zwallet.ui.layout.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.emrizkis.zwallet.data.ZWalletDataSource
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.response.ProfileResponse
import com.emrizkis.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel


class ProfileViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel() {
    fun getProfileInfo(): LiveData<Resource<APIResponse<ProfileResponse>?>> {
        return dataSource.getProfileInfo()
    }
}