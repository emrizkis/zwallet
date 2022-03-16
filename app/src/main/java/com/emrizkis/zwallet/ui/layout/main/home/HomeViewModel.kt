package com.emrizkis.zwallet.ui.layout.main.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.emrizkis.zwallet.data.ZWalletDataSource
import com.emrizkis.zwallet.data.api.ZWalletApi
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.Invoice
import com.emrizkis.zwallet.model.UserDetail
import com.emrizkis.zwallet.network.NetworkConfig
import com.emrizkis.zwallet.utils.Resource

class HomeViewModel(app: Application): ViewModel() {
    private var apiClient: ZWalletApi = NetworkConfig(app).buildApi()
    private var dataSource = ZWalletDataSource(apiClient)

    fun getInvoice(): LiveData<Resource<APIResponse<List<Invoice>>?>> {
        return dataSource.getInvoice()
    }

    fun getBalance(): LiveData<Resource<APIResponse<List<UserDetail>>?>> {
        return dataSource.getBalance()
    }
}