package com.cai.user.ui.activity

import android.os.Bundle
import com.cai.base.ui.activity.BaseMvpActivity
import com.cai.user.R
import com.cai.user.injection.component.DaggerUserComponent
import com.cai.user.injection.module.UserModule
import com.cai.user.presenter.RegisterPresenter
import com.cai.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onRegisterResult(result: Boolean) {
        if (result) {
            toast("注册成功")
        } else {
            toast("注册失败")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initInjection()

        mRegBtn.setOnClickListener {
            mPresenter.register(mPhoneEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
        }
    }

    fun initInjection() {
        DaggerUserComponent.builder().userModule(UserModule()).build().inject(this)
        mPresenter.mView = this

    }

}
