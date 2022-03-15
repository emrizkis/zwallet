package com.emrizkis.zwallet.ui.auth.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.emrizkis.zwallet.data.ZWalletDataSource
import com.emrizkis.zwallet.data.api.ZWalletApi
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.User
import com.emrizkis.zwallet.network.NetworkConfig
import com.emrizkis.zwallet.utils.Resource

class LoginViewModel(app: Application): ViewModel() {
    private var apiClient: ZWalletApi = NetworkConfig(app).buildApi()
    private var dataSource = ZWalletDataSource(apiClient)

    fun login(email: String, password: String) : LiveData<Resource<APIResponse<User>?>> {
        return dataSource.login(email, password)
    }

}