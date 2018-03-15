package com.cai.user.presenter

import com.cai.base.presenter.BasePresenter
import com.cai.user.presenter.view.RegisterView

class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register(mobile: String, pwd: String, verifyCode: String) {

        mView.onRegisterResult(true)
    }
}