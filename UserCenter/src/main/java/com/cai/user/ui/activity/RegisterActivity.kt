package com.cai.user.ui.activity

import android.os.Bundle
import com.cai.base.ui.activity.BaseMvpActivity
import com.cai.user.R
import com.cai.user.presenter.RegisterPresenter
import com.cai.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onRegisterResult(result: Boolean) {
        toast("注册成功")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPresenter = RegisterPresenter()
        mPresenter.mView = this
        mRegBtn.setOnClickListener {
            mPresenter.register(mPhoneEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
        }
    }

}
