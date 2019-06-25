package com.carter.androiddemo.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.carter.androiddemo.MyApplication
import me.yokeyword.fragmentation.SupportFragment

abstract class AbstractSimpleFragment: SupportFragment() {

    private lateinit var unBinder:Unbinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutId(),container,false)
        unBinder = ButterKnife.bind(this,view)
        initView()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (unBinder != Unbinder.EMPTY){
            unBinder.unbind()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val refWatcher = MyApplication.getRefWatcher(_mActivity)
        refWatcher.watch(this)
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        initEventAndData()
    }

    /**
     * 有些初始化必须在onCreateView中，例如setAdapter,
     * 否则，会弹出 No adapter attached; skipping layout
     */
    protected abstract fun initView()

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化数据
     */
    protected abstract fun initEventAndData()
}