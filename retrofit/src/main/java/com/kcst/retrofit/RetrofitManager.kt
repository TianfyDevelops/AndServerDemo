package com.kcst.retrofit

import android.text.TextUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager private constructor() {

    companion object {
        val INSTANCE = SingleHolder.holder
        const val DEFAULT_URL="http://192.168.1.122:8080/"
    }

    private object SingleHolder {
        val holder = RetrofitManager()
    }


    lateinit var retrofit: Retrofit

    /**
     * 初始化Retrofit
     * @param baseUrl endIndex must "/"
     */
    fun initRetrofit(baseUrl: String?) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .writeTimeout(5000, TimeUnit.MILLISECONDS)
            .build()
        val url = if (TextUtils.isEmpty(baseUrl)) DEFAULT_URL else baseUrl!!
        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }
}