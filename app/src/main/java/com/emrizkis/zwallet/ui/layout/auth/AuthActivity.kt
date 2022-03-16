package com.emrizkis.zwallet.ui.layout.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emrizkis.zwallet.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}