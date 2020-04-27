package com.bmf.myapplication.api

import android.content.Context
import android.util.Log
import com.bmf.myapplication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

class NetUtil(private val weak: WeakReference<Context>) {

    var service: Service

    private var client: OkHttpClient

    init {
        //初始化拦截器
        val netInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("theOne", "网络请求Log:$it")
        })
        if (BuildConfig.DEBUG) {
            netInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            netInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
        //初始化client
        client = OkHttpClient.Builder()
            .addInterceptor {
                //创建一个新的请求builder
                val builder = it.request().newBuilder()

                //添加请求头
                builder.addHeader("Authorization", "Bearer ")
                    .addHeader("platform", "android")
                    .addHeader("lang", "zh-CHS")

                val request = builder.build()
                return@addInterceptor it.proceed(request)
            }
            .addNetworkInterceptor(netInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

        //初始化retrofit
        val retrofit = Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Service.baseUrl)
            .build()

        service = retrofit.create(Service::class.java)
    }


    /**
     * 使用单利
     */
    companion object {
        private var inStance: NetUtil? = null
        @JvmStatic
        fun instance(context: Context): NetUtil {
            return inStance ?: NetUtil(WeakReference(context)).apply {
                inStance = this
            }
        }
    }


}