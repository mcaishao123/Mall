package com.cai.base.wigets

import android.content.Context
import android.widget.ImageView
import com.cai.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/*
    Banner图片加载器
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadUrlImage(context, path.toString(), imageView)
    }
}
