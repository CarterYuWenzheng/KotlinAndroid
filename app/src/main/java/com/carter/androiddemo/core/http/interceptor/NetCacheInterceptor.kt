package com.carter.androiddemo.core.http.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetCacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)
        val onlineCacheTime = 0//在线的时候的缓存过期时间，如果想要不缓存，直接时间设置为0
        return response.newBuilder()
                .header("Cache-Control", "public, max-age=" + onlineCacheTime)
                .removeHeader("Pragma")
                .build()
    }

}