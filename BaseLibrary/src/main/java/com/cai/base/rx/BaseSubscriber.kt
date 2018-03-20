package com.cai.base.rx

import com.cai.base.presenter.view.BaseView
import rx.Subscriber

open class BaseSubscriber<T>(val baseView:BaseView): Subscriber<T>() {

    override fun onNext(t: T) {
        baseView.hideLoading()
    }

    override fun onError(e: Throwable?) {
        baseView.hideLoading()
        if (e is BaseException){
            baseView.onError(e.msg)
        }
    }

    override fun onCompleted() {

    }

}