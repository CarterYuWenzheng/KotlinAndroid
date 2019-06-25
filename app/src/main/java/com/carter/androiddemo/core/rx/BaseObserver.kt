package com.carter.androiddemo.core.rx

import android.support.annotation.CallSuper
import android.text.TextUtils
import android.util.Log
import com.carter.androiddemo.MyApplication
import com.carter.androiddemo.R
import com.carter.androiddemo.base.view.IView
import com.carter.androiddemo.core.http.BaseResponse
import com.carter.androiddemo.core.http.exception.ServiceException
import com.carter.androiddemo.utils.CommenUtil
import io.reactivex.observers.ResourceObserver
import retrofit2.HttpException

@Suppress("DEPRECATED_IDENTITY_EQUALS")
abstract class BaseObserver<T>: ResourceObserver<BaseResponse<T>>{
    private val TAG = "BaseObserver"

    private var mView: IView? = null
    private var mErrorMsg: String? = null
    private var isShowStatusView = true

    constructor(view: IView) {
        this.mView = view
    }

    constructor(view: IView, errorMsg: String){
        this.mView = view
        this.mErrorMsg = errorMsg
    }

    constructor(view: IView, isShowStatusView: Boolean){
        this.mView = view
        this.isShowStatusView = isShowStatusView
    }

    protected fun BaseObserver(view: IView, errorMsg: String, isShowStatusView: Boolean){
        this.mView = view
        this.mErrorMsg = errorMsg
        this.isShowStatusView = isShowStatusView
    }

    abstract fun onSuccess(t: T)

    @CallSuper
    fun onFailure(code: Int, message: String) {
        mView!!.showErrorMsg(message)
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        if (isShowStatusView) {
            mView!!.showLoading()
        }
    }

    override fun onNext(baseResponse: BaseResponse<T>) {
        if (baseResponse.getErrorCode() === BaseResponse.SUCCESS) {
            Log.d(TAG, "onSuccess")
            if (isShowStatusView) {
                mView!!.hideLoading()
                mView!!.showContent()
            }
            onSuccess(baseResponse.getData()!!)
        } else {
            Log.d(TAG, "onFailure")
            if (isShowStatusView) {
                mView!!.hideLoading()
                mView!!.showContent()
            }
            onFailure(baseResponse.getErrorCode(), baseResponse.getErrorMsg()!!)
        }
    }

    override fun onComplete() {
        Log.d(TAG, "onComplete")
        if (mView == null) {
            return
        }
        if (!CommenUtil.isNetworkConnected()) {
            mView!!.showErrorMsg(MyApplication.context.getString(R.string.http_error))
        }

    }

    override fun onError(e: Throwable) {
        Log.d(TAG, "onError")
        if (mView == null) {
            return
        }
        if (isShowStatusView) {
            mView!!.hideLoading()
        }
        if (e is HttpException) {
            mView!!.showErrorMsg(MyApplication.context.getString(R.string.http_error))
            if (isShowStatusView) {
                mView!!.showNoNetwork()
            }
        } else if (e is ServiceException) {
            mView!!.showErrorMsg(e.toString())
            if (isShowStatusView) {
                mView!!.showError()
            }
        } else {
            if (!TextUtils.isEmpty(mErrorMsg)) {
                mView!!.showErrorMsg(mErrorMsg!!)
            }
            if (isShowStatusView) {
                mView!!.showError()
            }
            Log.e(TAG, e.toString())
        }
    }
}