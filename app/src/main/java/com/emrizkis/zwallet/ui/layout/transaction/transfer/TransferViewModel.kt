package com.emrizkis.zwallet.ui.layout.transaction.transfer

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.engine.Resource
import com.emrizkis.zwallet.data.ZWalletDataSource
import com.emrizkis.zwallet.data.api.ZWalletApi
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.ContactReceiver
import com.emrizkis.zwallet.model.request.TransferRequest
import com.emrizkis.zwallet.network.NetworkConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel

class TransferViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel() {
//    private var apiClient: ZWalletApi = NetworkConfig(app).buildApi()
//    private var dataSource = ZWalletDataSource(apiClient)
    private var selectedContact = MutableLiveData<ContactReceiver>()
    private var transfer = MutableLiveData<TransferRequest>()

    fun getContact(): LiveData<com.emrizkis.zwallet.utils.Resource<APIResponse<List<ContactReceiver>>?>> {
        return dataSource.getContact()
    }

    fun setSelectedContact(contact: ContactReceiver) {
        selectedContact.value = contact
    }

    fun getSelectedContact(): MutableLiveData<ContactReceiver> {
        return selectedContact
    }

    fun setTransferParam(data: TransferRequest) {
        transfer.value = data
    }

    fun getTransferParam(): MutableLiveData<TransferRequest> {
        return transfer
    }
}