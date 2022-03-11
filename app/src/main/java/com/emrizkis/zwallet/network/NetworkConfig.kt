package com.emrizkis.zwallet.network

import android.content.Context
import com.emrizkis.zwallet.data.api.ZWalletApi
import com.emrizkis.zwallet.utils.BASE_URL
import com.emrizkis.zwallet.utils.KEY_USER_REFRESH_TOKEN
import com.emrizkis.zwallet.utils.KEY_USER_TOKEN
import com.emrizkis.zwallet.utils.PREFS_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfig(
//    perlu passing context
    val context: Context?
) {
    fun getInterceptor(): OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val prefs = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val token = prefs?.getString(KEY_USER_TOKEN, "")
        val client = OkHttpClient.Builder()

        if(!token.isNullOrEmpty()){
            client.addInterceptor(TokenInterceptor(context))
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
}