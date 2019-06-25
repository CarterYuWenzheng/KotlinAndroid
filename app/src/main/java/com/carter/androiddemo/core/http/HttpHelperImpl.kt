package com.carter.androiddemo.core.http

import com.carter.androiddemo.core.http.api.ApiService
import com.carter.androiddemo.modules.login.bean.LoginData
import io.reactivex.Observable
import javax.inject.Inject

class HttpHelperImpl @Inject constructor( var apiService: ApiService) : HttpHelper {

    override fun register(username: String, password: String, rePassWord: String): Observable<BaseResponse<LoginData>> {
        return apiService.register(username, password, rePassWord)
    }

}