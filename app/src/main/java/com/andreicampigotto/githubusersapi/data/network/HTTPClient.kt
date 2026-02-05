package com.andreicampigotto.githubusersapi.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

private const val BASE_URL = "https://api.github.com/"

class HTTPClient {

    private val provideRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun <T : Any> create(clazz: KClass<T>): T = provideRetrofit.create(clazz.java)
}