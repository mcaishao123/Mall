package com.cai.base.ext

import com.cai.base.data.protocol.BaseResp
import com.cai.base.rx.BaseFunc
import com.cai.base.rx.BaseFuncBoolean
import com.cai.base.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun <T> Observable<T>.excute(subscriber: BaseSubscriber<T>,lifecycleProvider: LifecycleProvider<*>) {

    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)

}

fun <T> Observable<BaseResp<T>>.convert() :Observable<T>{
    return this.flatMap(BaseFunc())
}

fun <T> Observable<BaseResp<T>>.convertBoolean():Observable<Boolean>{
    return this.flatMap(BaseFuncBoolean())
}

