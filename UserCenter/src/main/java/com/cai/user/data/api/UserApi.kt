package com.cai.user.data.api

import com.cai.base.data.protocol.BaseResp
import com.cai.user.data.protocol.LoginReq
import com.cai.user.data.protocol.RegisterReq
import com.cai.user.data.protocol.UserInfo
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserApi {

    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>


    @POST("userCenter/login")
    fun login(@Body req: LoginReq): Observable<BaseResp<UserInfo>>
}