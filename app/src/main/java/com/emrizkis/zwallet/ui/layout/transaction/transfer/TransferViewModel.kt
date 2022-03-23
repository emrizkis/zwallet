package com.emrizkis.zwallet.ui.layout.transaction.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emrizkis.zwallet.data.ZWalletDataSource
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.response.ReceiverResponse
import com.emrizkis.zwallet.model.response.TransferResponse
import com.emrizkis.zwallet.model.request.TransferRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel

class TransferViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel() {
//    private var apiClient: ZWalletApi = NetworkConfig(app).buildApi()
//    private var dataSource = ZWalletDataSource(apiClient)
    private var selectedContact = MutableLiveData<ReceiverResponse>()
    private var transfer = MutableLiveData<TransferRequest>()
    val balance = MutableLiveData<Int>()

    fun getContact(): LiveData<com.emrizkis.zwallet.utils.Resource<APIResponse<List<ReceiverResponse>>?>> {
        return dataSource.getContact()
    }

    fun setSelectedContact(contact: ReceiverResponse) {
        selectedContact.value = contact
    }

    fun getSelectedContact(): MutableLiveData<ReceiverResponse> {
        return selectedContact
    }

    fun setTransferParam(data: TransferRequest) {
        transfer.value = data
    }

    fun getTransferParam(): MutableLiveData<TransferRequest> {
        return transfer
    }

    fun transferAmount(pin: String) : LiveData<com.emrizkis.zwallet.utils.Resource<APIResponse<TransferResponse>?>> {
        transfer.value?.receiver = selectedContact.value?.id.toString()
        return dataSource.transferAmount(transfer.value!!, pin = pin)

    }
}