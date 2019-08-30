package com.redbox.boarder.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

object NetworkModule {
    val retrofit : Retrofit
        get() {

            val interceptorLogger = HttpLoggingInterceptor()
            interceptorLogger.level = (HttpLoggingInterceptor.Level.BASIC)
            val clientLogger = OkHttpClient.Builder().addInterceptor(interceptorLogger).build()

            return Retrofit.Builder()
                .client(clientLogger)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://a.4cdn.org/")
                .build()
        }
}