package com.carter.androiddemo.core.http.interceptor

import com.carter.androiddemo.utils.CommenUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class OfflineCacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if (!CommenUtil.isNetworkConnected()) {
            val offlineCacheTime = 60 * 60 * 24 * 28//离线的时候的缓存的过期时间,4周
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$offlineCacheTime")
                    .removeHeader("Pragma")
                    .build()
        }
        return chain.proceed(request)
    }

}