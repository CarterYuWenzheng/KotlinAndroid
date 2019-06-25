package com.carter.androiddemo.core.preference

import android.content.Context
import android.content.SharedPreferences
import com.carter.androiddemo.MyApplication
import com.carter.androiddemo.core.constant.Constant
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PreferenceHelperImpl @Inject constructor() : PreferenceHelper {

    val mPreferences: SharedPreferences

    init {
        mPreferences = MyApplication.context.getSharedPreferences(Constant.SP.SHARED_PREFERENCE,Context.MODE_PRIVATE)

    }

    override fun setLoginState(boolean: Boolean) {
        mPreferences.edit().putBoolean(Constant.SP.LOGIN_STATUS,boolean).apply()
    }

    override fun getLoginState(): Boolean {
        return mPreferences.getBoolean(Constant.SP.LOGIN_STATUS,false)
    }

    override fun setLoginAccount(account: String) {
        mPreferences.edit().putString(Constant.SP.ACCOUNT,account).apply()
    }

    override fun getLoginAccount(): String {
        return mPreferences.getString(Constant.SP.ACCOUNT,"")
    }

    override fun setNightMode(boolean: Boolean) {
        mPreferences.edit().putBoolean(Constant.SP.NIGHT_MODE,boolean).apply()
    }

    override fun getNightMode(): Boolean {
        return mPreferences.getBoolean(Constant.SP.NIGHT_MODE,false)
    }

}