package com.cai.user.presenter

import com.cai.base.ext.excute
import com.cai.base.presenter.BasePresenter
import com.cai.base.rx.BaseSubscriber
import com.cai.user.presenter.view.ForgetPwdView
import com.cai.user.service.UserService
import javax.inject.Inject

class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun forgetPwd(mobile: String, verifyCode: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        userService.forgetPwd(mobile, verifyCode)
                .excute(object : BaseSubscriber<Boolean>(mView) {

                    override fun onNext(t: Boolean) {
                        if (t)
                            mView.onForgetPwd("验证成功")
                    }

                }, lifecycleProvider)
    }
}