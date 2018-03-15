package com.cai.user.service.impl

import com.cai.user.service.UserService
import rx.Observable

/**
 * Created by zhengmc on 2018/3/16.
 */
class UserServiceImpl : UserService{

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return Observable.just(true)
    }


}