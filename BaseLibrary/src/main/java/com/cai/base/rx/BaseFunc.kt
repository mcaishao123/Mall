package com.cai.base.rx

import com.cai.base.common.ResultCode
import com.cai.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by zhengmc on 2018/3/17.
 */
class BaseFunc<T> : Func1<BaseResp<T>, Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }

}