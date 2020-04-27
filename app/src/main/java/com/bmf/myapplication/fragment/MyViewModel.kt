package com.bmf.myapplication.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.bmf.myapplication.bean.PojoBean
import com.bmf.myapplication.net.INet

class MyViewModel(application: Application) : AndroidViewModel(application) {
    val time: Int = 0
    val data: LiveData<MutableList<PojoBean>> = MediatorLiveData<MutableList<PojoBean>>()
    fun test() {
        val tse = Transformations.map(data) {


        }
    }


}