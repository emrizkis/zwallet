package com.emrizkis.zwallet.ui.layout.auth.pinauth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.emrizkis.zwallet.data.ZWalletDataSource
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PinAuthViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel(){
    fun checkPin(getpin: String): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.checkPin(getpin)
    }
}