package com.emrizkis.zwallet.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.ui.auth.AuthActivity
import com.emrizkis.zwallet.utils.KEY_LOGGED_IN
import com.emrizkis.zwallet.utils.PREFS_NAME

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


        if (prefs.getBoolean(KEY_LOGGED_IN, false)){

            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 500)

        } else{
            Handler().postDelayed({
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }, 500)

        }

    }
}