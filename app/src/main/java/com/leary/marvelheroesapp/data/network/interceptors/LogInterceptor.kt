package com.leary.marvelheroesapp.data.network.interceptors

import okhttp3.logging.HttpLoggingInterceptor

class LogInterceptor {
    companion object{
        val loggingInterceptor = HttpLoggingInterceptor()
            .apply {
                setLevel(HttpLoggingInterceptor.Level.BASIC)
            }
    }

}