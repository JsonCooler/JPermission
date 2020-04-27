package com.bmf.myapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.core.text.buildSpannedString
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bmf.myapplication.api.BannerBean
import com.bmf.myapplication.net.request
import com.bmf.myapplication.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class IViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RemoteRepository(application)
    private val _bannerList = ObservableArrayList<BannerBean>()
    val bannerList = _bannerList
    //获取banner
    fun getBanner() {
        request(success = {
            val result = repository.getBanner()
            if (result.code == 1) {
                _bannerList.clear()
                result.data?.list?.let { _bannerList.addAll(it) }
            }
        },
            fail = {
                Log.d("test", "协程抛出异常${it}")
            })
    }

}