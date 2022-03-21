package com.emrizkis.zwallet.ui.layout.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emrizkis.zwallet.data.ZWalletDataSource
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.request.TransferRequest
import com.emrizkis.zwallet.model.response.InvoiceResponse
import com.emrizkis.zwallet.model.response.HomeUserResponse
import com.emrizkis.zwallet.model.response.ReceiverResponse
import com.emrizkis.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class HomeViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel() {
//    private var apiClient: ZWalletApi = NetworkConfig(app).buildApi()
//    private var dataSource = ZWalletDataSource(apiClient)

    private var dataProfile = MutableLiveData<Int>()


    fun getInvoice(): LiveData<Resource<APIResponse<List<InvoiceResponse>>?>> {
        return dataSource.getInvoice()
    }

    fun getBalance(): LiveData<Resource<APIResponse<List<HomeUserResponse>>?>> {
//        dataProfile = dataSource.getBalance().toString()
        return dataSource.getBalance()
    }

    fun setDataProfile(data: Int) {
        dataProfile.value = data
    }

    fun getDataProfile(): MutableLiveData<Int> {
        return dataProfile
    }

}