package com.bmf.myapplication.api

/**
 * 首页banner
 */
class BannerListBean {
    val list: MutableList<BannerBean> = mutableListOf()
}

data class BannerBean(
    val id: Int = -1,
    val cid: Int = -1,//广告分类id
    val title: String = "",//标题
    val image: String = "",//图片地址
    val url: String = "",//跳转外链或内部链接
    val is_in: Int = -1,//是否是内连
    val inlink_mark: String = "",//内联标识
    val inlink_id: String = "",//内联id
    val type: Int = 1,//默认为1图片  2 是视频
    val video: String = ""//视频url、
)