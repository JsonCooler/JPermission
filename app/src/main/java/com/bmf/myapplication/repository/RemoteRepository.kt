package com.bmf.myapplication.repository

import android.content.Context
import com.bmf.myapplication.api.BannerListBean
import com.bmf.myapplication.api.Result
import com.bmf.myapplication.net.INet
import com.bmf.myapplication.net.fetchData
import com.bmf.myapplication.net.test
import kotlinx.coroutines.*

class RemoteRepository(private val context: Context) {
    private var localRepository: LocalRepository? = null

    init {
        localRepository = LocalRepository(context)
    }

    suspend fun getBanner(): Result<BannerListBean> =
        fetchData(remote = {
            INet.api().getBanner()
        })


}