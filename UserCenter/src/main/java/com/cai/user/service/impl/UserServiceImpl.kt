package com.cai.user.service.impl

import com.cai.base.ext.convert
import com.cai.base.ext.convertBoolean
import com.cai.user.data.protocol.UserInfo
import com.cai.user.data.respository.UserRespository
import com.cai.user.service.UserService
import rx.Observable
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


    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return userRspository.login(mobile, pwd, pushId)
                .convert()
    }

    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return userRspository.forgetPwd(mobile, verifyCode)
                .convertBoolean()
    }

    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return userRspository.resetPwd(mobile, pwd).convertBoolean()
    }

    override fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo> {
        return userRspository.editUser(userIcon,userName,userGender,userSign).convert()
    }

}