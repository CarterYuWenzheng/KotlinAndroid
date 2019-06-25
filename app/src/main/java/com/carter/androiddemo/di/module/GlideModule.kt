package com.carter.androiddemo.di.module

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class GlideModule: AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
        val memoryCacheSizeBytes = 1024 * 1024 * 20
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
        val bitmapPoolSizeBytes = 1024 * 1024 * 30
        builder.setBitmapPool(LruBitmapPool(bitmapPoolSizeBytes.toLong()))
        val diskCacheSizeBytes = 1024 * 1024 * 100
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))
    }
}