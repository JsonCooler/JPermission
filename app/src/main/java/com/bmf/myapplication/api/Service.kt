package com.bmf.myapplication.api

import retrofit2.http.GET

interface Service {
    companion object {
        const val picImageUrl = "http://theone.ali.static.xinyu1234.xyz/"
        const val baseUrl = "https://theone.web.xinyu1234.xyz"//测试url
    }

    @GET("/web/super_ad/get_all_list")
    suspend fun getBanner(): Result<BannerListBean>
}