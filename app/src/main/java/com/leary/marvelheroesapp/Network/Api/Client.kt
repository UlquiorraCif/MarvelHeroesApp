package com.leary.marvelheroesapp.Network.Api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class ParceConstants{
    companion object{
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        const val limit = "10"

        const val API_KEY = "7925fa99ca83b2b8cfbcf63181783cd1"
        const val PRIVATE_API_KEY = "2ca0407a555296f830c22addaf7d6cb6dde0e0d0"

        fun hash():String{
            val inputHashString = "$ts$PRIVATE_API_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(inputHashString.toByteArray())).toString(16).padStart(32,'0')
        }
    }
}

val authInterceptor = Interceptor{chain ->
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

val loggingInterceptor = HttpLoggingInterceptor()
    .apply {
        setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

val client = OkHttpClient.Builder()
    .addNetworkInterceptor(loggingInterceptor)
    .addNetworkInterceptor(authInterceptor)
    .build()