package com.cai.base.presenter

import com.cai.base.presenter.view.BaseView

open class BasePresenter<T:BaseView> {

    lateinit var mView: T
}