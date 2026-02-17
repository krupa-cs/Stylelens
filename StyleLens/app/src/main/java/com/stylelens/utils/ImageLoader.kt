package com.stylelens.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ImageLoader {
    
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }
    
    fun loadCircularImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }
}
