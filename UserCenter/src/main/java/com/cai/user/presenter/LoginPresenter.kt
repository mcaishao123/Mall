package com.cai.user.presenter

import com.cai.base.ext.excute
import com.cai.base.presenter.BasePresenter
import com.cai.base.rx.BaseSubscriber
import com.cai.user.data.protocol.UserInfo
import com.cai.user.presenter.view.LoginView
import com.cai.user.service.UserService
import javax.inject.Inject

/**
 * Created by zhengmc on 2018/3/19.
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserService

    fun login(mobile: String, pwd: String, pushId: String) {
        if (!checkNetWork()) return

        mView.showLoading()

        userService.login(mobile, pwd, pushId).excute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.onLoginResult(t)
            }

        }, lifecycleProvider)
    }
}