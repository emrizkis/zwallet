package com.emrizkis.zwallet.ui.layout.transaction.topup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emrizkis.zwallet.databinding.ActivityTopupBinding

class TopupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTopupBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}