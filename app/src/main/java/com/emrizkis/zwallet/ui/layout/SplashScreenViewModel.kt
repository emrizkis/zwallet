package com.emrizkis.zwallet.ui.layout

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.emrizkis.zwallet.data.ZWalletDataSource
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.request.RefreshTokenRequest
import com.emrizkis.zwallet.model.response.LoginResponse
import com.emrizkis.zwallet.model.response.RefreshTokenResponse
import com.emrizkis.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel() {
    fun refreshTokenLogin(data: RefreshTokenRequest): LiveData<Resource<APIResponse<LoginResponse>?>> {
        return dataSource.refreshTokenLogin(data)
    }
}