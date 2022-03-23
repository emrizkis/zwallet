package com.emrizkis.zwallet.ui.layout.transaction.topup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emrizkis.zwallet.databinding.ActivityTopupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopupBinding
    private lateinit var phone: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTopupBinding.inflate(layoutInflater)

        phone = intent.getStringExtra("phone").toString()

        setContentView(binding.root)
    }

    fun getPhone(): String{
        return phone
    }
}