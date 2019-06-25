package com.carter.androiddemo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

object ToastUtil {
    private var mToast: Toast? = null

    @SuppressLint("ShowToast")
    fun showToast(mContext: Context, text: String, duration: Int) {

        if (mToast == null) {
            mToast = Toast.makeText(mContext.applicationContext, text, duration)
        } else {
            mToast!!.setText(text)
            mToast!!.duration = duration
        }
        mToast!!.show()
    }

    fun showToast(mContext: Context, resId: Int, duration: Int) {
        showToast(mContext, mContext.resources.getString(resId), duration)
    }

    fun showToast(context: Context, string: String) {
        showToast(context, string, Toast.LENGTH_SHORT)
    }
}