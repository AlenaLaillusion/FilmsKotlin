package com.example.fundamentalskotlin.api

import com.example.fundamentalskotlin.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MovieApiHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val request = originalRequest.newBuilder()
            .url(originalHttpUrl)
            .addHeader(API_KEY_HEADER, BuildConfig.API_KEY)
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY_HEADER = "api_key"
    }
}