package com.leary.marvelheroesapp.Data.Network.Interceptors

import okhttp3.logging.HttpLoggingInterceptor

class LogInterceptor {
    companion object{
        val loggingInterceptor = HttpLoggingInterceptor()
            .apply {
                setLevel(HttpLoggingInterceptor.Level.BASIC)
            }
    }

}