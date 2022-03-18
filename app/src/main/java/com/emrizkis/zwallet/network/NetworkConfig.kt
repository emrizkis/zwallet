package com.emrizkis.zwallet.network

import android.content.Context
import android.content.SharedPreferences
import com.emrizkis.zwallet.data.api.ZWalletApi
import com.emrizkis.zwallet.utils.BASE_URL
import com.emrizkis.zwallet.utils.KEY_USER_REFRESH_TOKEN
import com.emrizkis.zwallet.utils.KEY_USER_TOKEN
import com.emrizkis.zwallet.utils.PREFS_NAME
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetworkConfig(
//    perlu passing context
    val context: Context?

) {
    private val preferences = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getInterceptor(authenticator: Authenticator? = null): OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val prefs = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val token = prefs?.getString(KEY_USER_TOKEN, "")
        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)

        if(!token.isNullOrEmpty()){
            client.addInterceptor(TokenInterceptor{
                preferences?.getString(KEY_USER_TOKEN, "").toString()
            })
        }
        if(authenticator != null){
            client.authenticator(authenticator)
        }

        return client.build()
    }


    fun getRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun  getService():ZWalletApi{
        return getRetrofit().create(ZWalletApi::class.java)
    }

    fun buildApi(): ZWalletApi{
        val authenticator = RefreshTokenInterceptor(context, getService(), preferences!!)
        println("authenticator: ${authenticator.context}")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterceptor(authenticator))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ZWalletApi::class.java)
    }
}