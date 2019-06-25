package com.carter.androiddemo.base.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.SupportActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.carter.androiddemo.R
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.toolbar.view.*

@SuppressLint("RestrictedApi")
abstract class AbstractSimpleActivity : SupportActivity() {

    lateinit var unBinder: Unbinder

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutId())
        unBinder = ButterKnife.bind(this)
        onViewCreated()
        initView()
        initToolbar()
        initEventAndData()
        ImmersionBar.with(this)
                .statusBarView(R.id.status_bar_view)
                .keyboardEnable(true)
                .init()
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy() //必须调用该方法，防止内存泄漏
        if (unBinder != null && unBinder !== Unbinder.EMPTY) {
            unBinder.unbind()
        }
    }

    protected abstract fun initView()

    /**
     * 在initEventAndData()之前执行
     */
    protected abstract fun onViewCreated()

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化ToolBar
     */
    protected abstract fun initToolbar()

    /**
     * 初始化数据
     */
    protected abstract fun initEventAndData()
}