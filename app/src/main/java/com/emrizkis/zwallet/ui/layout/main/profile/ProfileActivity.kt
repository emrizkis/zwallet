package com.emrizkis.zwallet.ui.layout.main.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.emrizkis.zwallet.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

//    private lateinit var name: String
//    private lateinit var phone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)

//        name = intent.getStringExtra("name").toString()
//        phone = intent.getStringExtra("phone").toString()


        setContentView(binding.root)
    }

//    fun getName(): String{
//        return name
//    }
//
//    fun getPhone(): String{
//        return phone
//    }


}