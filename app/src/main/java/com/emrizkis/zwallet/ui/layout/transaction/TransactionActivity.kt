package com.emrizkis.zwallet.ui.layout.transaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emrizkis.zwallet.databinding.ActivityTransactionBinding

class TransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}