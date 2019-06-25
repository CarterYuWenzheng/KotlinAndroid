package com.carter.androiddemo.base.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.carter.androiddemo.R
import com.carter.androiddemo.base.presenter.IPresenter
import com.carter.androiddemo.base.view.IView
import com.carter.androiddemo.utils.CommenUtil
import com.carter.androiddemo.utils.ToastUtil
import com.classic.common.MultipleStatusView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.annotation.Nullable
import javax.inject.Inject

@SuppressLint("Registered")
abstract class BaseActivity<T : IPresenter<IView>> : AbstractSimpleActivity(), HasSupportFragmentInjector, IView {

    @Inject
    lateinit var mFragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var mPresenter: T

    lateinit var mMultipleStatusView: MultipleStatusView

    @SuppressLint("RestrictedApi")
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated() {
        val mNormalView: ViewGroup = findViewById(R.id.normal_view)
        mNormalView.visibility = View.GONE
        mMultipleStatusView = findViewById(R.id.custom_multiple_status_view)
        mMultipleStatusView.setOnRetryClickListener {
            mPresenter.reload()
        }
        mPresenter.attachView(view = this)
    }

    override fun onDestroy() {
        mPresenter.detachView()
        hideLoading()
        super.onDestroy()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentDispatchingAndroidInjector
    }

    override fun onBackPressed() {
        CommenUtil.hideKeyBoard(this, window.decorView.rootView)
        super.onBackPressed()
    }

    override fun showErrorMsg(errorMsg: String) {
        ToastUtil.showToast(this, errorMsg)
    }

    override fun showLoading() {
        mMultipleStatusView.showLoading()
    }

    override fun hideLoading() {
    }

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

    override fun handleLoginSuccess() {
    }

    override fun handleLogoutSuccess() {
    }

}