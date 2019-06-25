package com.carter.androiddemo.base.presenter

import com.carter.androiddemo.base.view.IView

interface IPresenter<T : IView> {

    fun attachView(view: T)

    fun detachView()

    fun reload()

    fun registerEventBus()

    fun unregisterEventBus()

    fun setLoginStatus(loginStatus: Boolean)

    fun getLoginStatus(): Boolean

    fun getLoginAccount(): String

    fun setLoginAccount(account: String)
}