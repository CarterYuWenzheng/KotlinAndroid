package com.carter.androiddemo.core.preference

interface PreferenceHelper {

    fun setLoginState(boolean: Boolean)
    fun getLoginState(): Boolean

    fun setLoginAccount(account: String)
    fun getLoginAccount(): String

    fun setNightMode(boolean: Boolean)
    fun getNightMode(): Boolean
}