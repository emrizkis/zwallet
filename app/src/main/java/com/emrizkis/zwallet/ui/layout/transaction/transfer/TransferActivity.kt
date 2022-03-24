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
    private lateinit var name: String
    private lateinit var phone: String
    private lateinit var imageSender: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        balance = intent.getStringExtra("balance").toString()
        name = intent.getStringExtra("name").toString()
        phone = intent.getStringExtra("phone").toString()
        imageSender = intent.getStringExtra("image").toString()
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun getBalance(): Int{
        return balance.toInt()
    }

    fun getName(): String{
        return name
    }

    fun getPhone(): String{
        return phone
    }

    fun getImage(): String{
        return imageSender
    }

}