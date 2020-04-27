package com.bmf.myapplication.bindadapter

import androidx.databinding.BindingAdapter
import com.bmf.myapplication.api.BannerBean
import com.bmf.myapplication.fragment.BannerImageLoader
import com.youth.banner.Banner
import com.youth.banner.Transformer

object MyBindAdapter {
    @JvmStatic
    @BindingAdapter("imgs")
    fun setBannerImages(banner: Banner, images: MutableList<BannerBean>) {
        banner.setImages(images)
            .isAutoPlay(true)
            .setBannerAnimation(Transformer.DepthPage)
            .setImageLoader(BannerImageLoader())
            .setDelayTime(4000)
            .setOnBannerListener {}
            .start()
    }
}