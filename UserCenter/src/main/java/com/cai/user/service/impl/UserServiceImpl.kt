package com.cai.user.service.impl

import com.cai.base.data.protocol.BaseResp
import com.cai.base.rx.BaseException
import com.cai.user.data.respository.UserRespository
import com.cai.user.service.UserService
import rx.Observable
import rx.functions.Func1

/**
 * Created by zhengmc on 2018/3/16.
 */
class UserServiceImpl : UserService{

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {

        return UserRespository().register(mobile, pwd, verifyCode)
                .flatMap(Func1<BaseResp<String>, Observable<Boolean>> { t ->
                    if (t.status != 0) {
                        return@Func1 Observable.error(BaseException(t.status, t.message))
                    }
                    Observable.just(true)
                })
    }


}