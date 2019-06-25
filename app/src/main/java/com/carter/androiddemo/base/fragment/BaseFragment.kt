package com.carter.androiddemo.base.fragment

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.carter.androiddemo.R
import com.carter.androiddemo.base.presenter.IPresenter
import com.carter.androiddemo.base.view.IView
import com.carter.androiddemo.utils.ToastUtil
import com.classic.common.MultipleStatusView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<T : IPresenter<IView>> : AbstractSimpleFragment(), IView {

    @Inject
    protected lateinit var mPresenter: T
    private lateinit var mMultipleStatusView: MultipleStatusView

    override fun onAttach(activity: Activity) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mNormalView = view.findViewById<LinearLayout>(R.id.normal_view)
        if (mNormalView != null) {
            mNormalView.visibility = View.GONE
        }
        mMultipleStatusView = view.findViewById(R.id.custom_multiple_status_view)
        mMultipleStatusView.setOnRetryClickListener { mPresenter.reload() }
        mPresenter.attachView(this)
    }

    override fun onDestroyView() {
        mPresenter.detachView()
        hideLoading()
        super.onDestroyView()
    }

    override fun showErrorMsg(errorMsg: String) {
        ToastUtil.showToast(_mActivity, errorMsg)
    }

    override fun showLoading() {
        mMultipleStatusView.showLoading()
    }

    override fun hideLoading() {}

    override fun showError() {
        mMultipleStatusView.showError()
    }

    override fun showNoNetwork() {
        mMultipleStatusView.showNoNetwork()
    }

    override fun showEmpty() {
        mMultipleStatusView.showEmpty()
    }

    override fun showContent() {
        mMultipleStatusView.showContent()
    }

    override fun handleLoginSuccess() {}

    override fun handleLogoutSuccess() {}
}