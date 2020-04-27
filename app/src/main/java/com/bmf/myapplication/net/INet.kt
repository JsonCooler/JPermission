package com.bmf.myapplication.net

import android.util.Log
import androidx.databinding.library.BuildConfig
import com.bmf.myapplication.api.Service
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object INet {
    private val retrofit = Retrofit.Builder()

    init {
        //初始化拦截器
        val netInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("theOne", "网络请求Log:$it")
        })

        netInterceptor.level = HttpLoggingInterceptor.Level.BODY

        //创建clientBuilder
        val netBuilder = OkHttpClient.Builder()
            .addInterceptor { setInterceptor(it) }
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addNetworkInterceptor(netInterceptor)
        //创建retrofit
        retrofit.baseUrl(Service.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(netBuilder.build())
    }

    //设置拦截器
    private fun setInterceptor(it: Interceptor.Chain): Response {
        //拦截请求重新创建请求 并添加请求头和其他参数
        //创建一个新的请求builder
        val builder = it.request().newBuilder()
        //添加请求头
        builder.addHeader("Authorization", ""/*"Bearer $token"*/)
            .addHeader("platform", "android")
            .addHeader("lang", "")
            .addHeader("version_number", "")

        val request = builder.build()
        return it.proceed(request)
    }

    fun api(): Service = retrofit.build().create(Service::class.java)
}