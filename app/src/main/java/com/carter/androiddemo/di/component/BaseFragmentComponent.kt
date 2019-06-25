package com.carter.androiddemo.di.component

import com.carter.androiddemo.base.fragment.BaseFragment
import com.carter.androiddemo.base.presenter.IPresenter
import com.carter.androiddemo.base.view.IView
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Subcomponent(modules = [AndroidInjectionModule::class])
interface BaseFragmentComponent : AndroidInjector<BaseFragment<IPresenter<IView>>> {

    @Subcomponent.Builder
    abstract class BaseBuilder : AndroidInjector.Builder<BaseFragment<IPresenter<IView>>>() {

    }
}