package com.emrizkis.zwallet.ui.layout.auth.pinauth

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.ActivityPinAuthBinding
import com.emrizkis.zwallet.ui.layout.main.profile.ProfileViewModel
import com.emrizkis.zwallet.utils.State
import dagger.hilt.android.AndroidEntryPoint
import java.net.HttpURLConnection

@AndroidEntryPoint
class PinAuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPinAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPinAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}