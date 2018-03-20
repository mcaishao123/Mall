package com.cai.base.presenter

import android.content.Context
import com.cai.base.presenter.view.BaseView
import com.cai.base.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

open class BasePresenter<T:BaseView> {

    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    fun checkNetWork():Boolean{
        if (NetWorkUtils.isNetWorkAvailable(context)) {
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}