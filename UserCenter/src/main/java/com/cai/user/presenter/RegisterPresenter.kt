package com.cai.user.presenter

import com.cai.base.ext.excute
import com.cai.base.presenter.BasePresenter
import com.cai.base.rx.BaseSubscriber
import com.cai.user.presenter.view.RegisterView
import com.cai.user.service.UserService
import javax.inject.Inject

class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, pwd: String, verifyCode: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        userService.register(mobile, pwd, verifyCode)
                .excute(object : BaseSubscriber<Boolean>(mView) {

                    override fun onNext(t: Boolean) {
                        if(t)
                        mView.onRegisterResult("注册成功")
                    }

                },lifecycleProvider)
    }
}