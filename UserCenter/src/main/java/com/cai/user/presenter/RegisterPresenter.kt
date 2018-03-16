package com.cai.user.presenter

import com.cai.base.ext.excute
import com.cai.base.presenter.BasePresenter
import com.cai.base.rx.BaseSubscriber
import com.cai.user.presenter.view.RegisterView
import com.cai.user.service.impl.UserServiceImpl

class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register(mobile: String, pwd: String, verifyCode: String) {

        val userService = UserServiceImpl();
        userService.register(mobile, pwd, verifyCode)
                .excute(object : BaseSubscriber<Boolean>() {

                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }

                })
    }
}