package com.carter.androiddemo.base.view

interface IView {

    /**
     * 显示错误信息
     */
    fun showErrorMsg(errorMsg: String)

    fun showLoading()

    fun hideLoading()

    fun showError()

    fun showNoNetwork()

    fun showEmpty()

    fun showContent()

    fun handleLoginSuccess()

    fun handleLogoutSuccess()
}