package com.emrizkis.zwallet.network

import android.content.Context
import com.emrizkis.zwallet.utils.KEY_USER_TOKEN
import com.emrizkis.zwallet.utils.PREFS_NAME
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val token: String?): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        if(!token.isNullOrEmpty()){
            request.header("Authorization", "Bearer $token")
        }

        return chain.proceed(request.build())

    }

}