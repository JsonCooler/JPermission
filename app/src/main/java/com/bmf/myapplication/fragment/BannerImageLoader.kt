package com.bmf.myapplication.fragment


import android.content.Context
import android.widget.ImageView
import com.bmf.myapplication.api.BannerBean
import com.bmf.myapplication.api.Service
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.loader.ImageLoader

/**
 * banner 的imageLoader
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        path?.let {
            if (it is BannerBean) {
                imageView?.let { view ->
                    context?.let { ct ->
                        Glide.with(ct).load(Service.picImageUrl + it.image)
                            .into(view)
                    }
                }
            }
        }
    }
}