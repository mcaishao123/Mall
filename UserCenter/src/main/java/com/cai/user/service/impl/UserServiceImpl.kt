package com.cai.user.service.impl

import com.cai.base.data.protocol.BaseResp
import com.cai.base.ext.convertBoolean
import com.cai.base.rx.BaseException
import com.cai.base.rx.BaseFuncBoolean
import com.cai.user.data.respository.UserRespository
import com.cai.user.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 * Created by zhengmc on 2018/3/16.
 */
class UserServiceImpl @Inject constructor(): UserService{

    @Inject
    lateinit var userRspository: UserRespository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {

        return userRspository.register(mobile, pwd, verifyCode)
                .convertBoolean()
    }


}