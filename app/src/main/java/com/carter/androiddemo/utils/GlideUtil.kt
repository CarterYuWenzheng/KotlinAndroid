package com.carter.androiddemo.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.carter.androiddemo.R
import com.carter.androiddemo.di.module.GlideApp

class GlideUtil {

    fun load(context: Context, url: String, iv: ImageView) {
        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.bg_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(iv)

    }
}