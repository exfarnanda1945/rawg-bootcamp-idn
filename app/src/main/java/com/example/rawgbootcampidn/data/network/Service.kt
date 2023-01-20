package com.example.rawgbootcampidn.data.network

import com.example.rawgbootcampidn.data.network.api.GameApi
import com.example.rawgbootcampidn.data.network.config.ApiInterceptor
import com.example.rawgbootcampidn.utils.Constant.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Service {
    private val client = OkHttpClient
        .Builder()
        .addInterceptor(ApiInterceptor())
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val GameService: GameApi by lazy {
        retrofit.create(GameApi::class.java)
    }
}