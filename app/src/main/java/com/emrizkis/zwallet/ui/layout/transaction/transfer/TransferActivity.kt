package com.emrizkis.zwallet.ui.layout.transaction.transfer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.emrizkis.zwallet.databinding.ActivityTransferBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferBinding
    private lateinit var balance: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        balance = intent.getStringExtra("balance").toString()
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun getBalance(): Int{
        return balance.toInt()
    }
}