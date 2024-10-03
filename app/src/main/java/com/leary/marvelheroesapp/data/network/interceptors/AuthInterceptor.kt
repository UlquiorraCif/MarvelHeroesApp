package com.leary.marvelheroesapp.data.network.interceptors

import com.leary.marvelheroesapp.data.network.api.ParceConstants
import okhttp3.Interceptor

class AuthInterceptor {
    companion object {
        val authInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()

            val newHttpUrl = originalRequest.url.newBuilder()
                .addQueryParameter("apikey", ParceConstants.API_KEY)
                .addQueryParameter("ts", ParceConstants.ts)
                .addQueryParameter("hash", ParceConstants.hash())
                .addQueryParameter("limit", ParceConstants.limit)
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(newHttpUrl)
                .build()

            chain.proceed(newRequest)
        }
    }
}