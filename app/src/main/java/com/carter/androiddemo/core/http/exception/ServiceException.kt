package com.carter.androiddemo.core.http.exception

class ServiceException : Exception {

    private var code: Int = 0

    constructor(message: String) {
        super.message
    }

    constructor(message: String, code: Int) {
        super.message
        this.code = code
    }

    public fun setCode(code: Int) {
        this.code = code
    }

    public fun getCode(): Int {
        return code
    }

}