package com.carter.androiddemo.modules.main.contract

import com.carter.androiddemo.base.presenter.IPresenter
import com.carter.androiddemo.base.view.IView

interface MainContract {

    interface View: IView{

    }

    interface Presenter:IPresenter<View>{

    }
}