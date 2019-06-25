package com.carter.androiddemo.core.http

class BaseResponse<T> {

    companion object {
        const val SUCCESS = 0
        const val FAIL = 1
    }

    /**
     * 0：成功，1：失败
     */
    private var errorCode: Int = 0

    private var errorMsg: String? = null

    private var data: T? = null

    fun getErrorCode(): Int {
        return errorCode
    }

    fun setErrorCode(errorCode: Int) {
        this.errorCode = errorCode
    }

    fun getErrorMsg(): String? {
        return errorMsg
    }

    fun setErrorMsg(errorMsg: String) {
        this.errorMsg = errorMsg
    }

    fun getData(): T? {
        return data
    }

    fun setData(data: T) {
        this.data = data
    }
}