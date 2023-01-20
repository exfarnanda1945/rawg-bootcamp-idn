package com.example.rawgbootcampidn.data.network.config

import com.example.rawgbootcampidn.utils.Constant.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url()
            .newBuilder()
            .addQueryParameter("key", API_KEY)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(url)
                .build()
        )
    }
}