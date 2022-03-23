package com.emrizkis.zwallet.ui.layout.transaction.topup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emrizkis.zwallet.data.ZWalletDataSource
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.request.TopupRequest
import com.emrizkis.zwallet.model.request.TransferRequest
import com.emrizkis.zwallet.model.response.ReceiverResponse
import com.emrizkis.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TopupViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel() {

    private var phone = MutableLiveData<String>()
    private var amount = MutableLiveData<String>()

    fun topupBalance(phone: String, amount: String): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.topupBalance(phone = phone, amount = amount)
    }

    fun setAmount(data: String){
        amount.value = data
    }
    fun setPhone(data: String){
        phone.value = data
    }

    fun getAmount(): MutableLiveData<String>{
        return amount
    }

    fun getPhone(): MutableLiveData<String>{
        return phone
    }

}