package com.carter.androiddemo.modules.main.presenter

import com.carter.androiddemo.base.presenter.BasePresenter
import com.carter.androiddemo.modules.main.contract.MainContract
import org.simple.eventbus.EventBus
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter<MainContract.View>(),MainContract.Presenter {

    override fun registerEventBus() {
        EventBus.getDefault().register(this)
    }

    override fun unregisterEventBus() {
        EventBus.getDefault().unregister(this)
    }
}