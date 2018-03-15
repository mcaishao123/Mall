package com.cai.user.service

import rx.Observable

/**
 * Created by zhengmc on 2018/3/16.
 */
interface UserService{
    //用户注册
    fun register(mobile: String, pwd: String,verifyCode: String): Observable<Boolean>
}