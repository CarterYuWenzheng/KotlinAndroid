package com.carter.androiddemo.core.http.api

import com.carter.androiddemo.core.http.BaseResponse
import com.carter.androiddemo.modules.login.bean.LoginData
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com/"
    }

    /**
     * 注册
     * https://www.wanandroid.com/user/register
     *
     * @param userName   user name
     * @param passWord   password
     * @param rePassWord re password
     * @return 注册数据
     */
    @POST("user/register")
    @FormUrlEncoded
    fun register(@Field("userName") userName: String, @Field("passWord") passWord: String, @Field("rePassWord") rePassWord: String): Observable<BaseResponse<LoginData>>
}