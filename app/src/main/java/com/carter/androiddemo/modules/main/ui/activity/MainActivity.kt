package com.carter.androiddemo.modules.main.ui.activity

import android.annotation.SuppressLint
import android.widget.Button
import butterknife.BindView
import com.carter.androiddemo.R
import com.carter.androiddemo.base.activity.BaseActivity
import com.carter.androiddemo.base.presenter.IPresenter
import com.carter.androiddemo.base.view.IView
import com.carter.androiddemo.modules.main.contract.MainContract
import com.carter.androiddemo.modules.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.content_main.*

@SuppressLint("Registered")
class MainActivity: BaseActivity<IPresenter<IView>>(),MainContract.View {

    override fun initView() {
        initListener()
    }

    private fun initListener() {
        btn_get.setOnClickListener {

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initToolbar() {

    }

    override fun initEventAndData() {

    }
}