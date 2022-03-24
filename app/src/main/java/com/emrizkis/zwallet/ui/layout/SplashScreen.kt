package com.emrizkis.zwallet.ui.layout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.data.api.ZWalletApi
import com.emrizkis.zwallet.databinding.ActivitySplashScreenBinding
import com.emrizkis.zwallet.model.request.RefreshTokenRequest
import com.emrizkis.zwallet.ui.layout.auth.AuthActivity
import com.emrizkis.zwallet.ui.layout.auth.pinauth.PinAuthActivity
import com.emrizkis.zwallet.ui.layout.main.home.MainActivity
import com.emrizkis.zwallet.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


        if (prefs.getBoolean(KEY_LOGGED_IN, false)){

            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 500)

        } else  {
            Handler().postDelayed({
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }, 500)

        }

    }
}