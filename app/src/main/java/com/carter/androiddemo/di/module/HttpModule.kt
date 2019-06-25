package com.carter.androiddemo.di.module

import com.carter.androiddemo.BuildConfig
import com.carter.androiddemo.MyApplication
import com.carter.androiddemo.core.http.api.ApiService
import com.carter.androiddemo.core.http.interceptor.NetCacheInterceptor
import com.carter.androiddemo.core.http.interceptor.OfflineCacheInterceptor
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class HttpModule {

    @Singleton
    @Provides
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java!!)
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit {
        return createRetrofit(builder, client, ApiService.BASE_URL)
    }

    @Singleton
    @Provides
    internal fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Singleton
    @Provides
    internal fun provideOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            builder.addInterceptor(loggingInterceptor)
        }
        //设置缓存
        val cacheFile = File(MyApplication.context.getCacheDir(), "cache")
        val cache = Cache(cacheFile, (1024 * 1024 * 50).toLong()) //50M
        builder.addNetworkInterceptor(NetCacheInterceptor())
        builder.addInterceptor(OfflineCacheInterceptor())
        builder.cache(cache)
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)
        //错误重连
        builder.retryOnConnectionFailure(true)
        //cookie认证
        builder.cookieJar(PersistentCookieJar(SetCookieCache(),
                SharedPrefsCookiePersistor(MyApplication.context)))
        return builder.build()
    }

    private fun createRetrofit(builder: Retrofit.Builder, client: OkHttpClient, url: String): Retrofit {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}