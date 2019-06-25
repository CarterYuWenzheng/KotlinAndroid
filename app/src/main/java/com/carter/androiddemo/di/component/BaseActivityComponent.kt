package com.carter.androiddemo.di.component

import com.carter.androiddemo.base.activity.BaseActivity
import com.carter.androiddemo.base.presenter.IPresenter
import com.carter.androiddemo.base.view.IView
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Subcomponent(modules = [AndroidInjectionModule::class])
interface BaseActivityComponent: AndroidInjector<BaseActivity<IPresenter<IView>>> {

    @Subcomponent.Builder
    abstract class BaseBuilder: AndroidInjector.Builder<BaseActivity<IPresenter<IView>>>() {

    }
}