package com.cai.user.data.respository

import com.cai.base.data.net.RetrofitFactory
import com.cai.base.data.protocol.BaseResp
import com.cai.user.data.api.UserApi
import com.cai.user.data.protocol.RegisterReq
import rx.Observable
import javax.inject.Inject

class UserRespository @Inject constructor() {

    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance
                .create(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verifyCode))
    }
}