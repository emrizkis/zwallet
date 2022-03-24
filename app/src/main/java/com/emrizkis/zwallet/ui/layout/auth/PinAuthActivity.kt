package com.emrizkis.zwallet.ui.layout.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emrizkis.zwallet.databinding.ActivityAuthBinding
import com.emrizkis.zwallet.databinding.ActivityPinAuthBinding

class PinAuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPinAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPinAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}