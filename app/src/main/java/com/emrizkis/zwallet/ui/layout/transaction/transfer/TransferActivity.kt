package com.emrizkis.zwallet.ui.layout.transaction.transfer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emrizkis.zwallet.databinding.ActivityTransferBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}