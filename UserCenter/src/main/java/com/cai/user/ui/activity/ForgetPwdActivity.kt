package com.cai.user.ui.activity

import android.os.Bundle
import android.view.View
import com.cai.base.ext.enable
import com.cai.base.ext.onClick
import com.cai.base.ui.activity.BaseMvpActivity
import com.cai.user.R
import com.cai.user.injection.component.DaggerUserComponent
import com.cai.user.injection.module.UserModule
import com.cai.user.presenter.ForgetPwdPresenter
import com.cai.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {


    override fun onForgetPwd(result: String) {
        toast(result)
        startActivity<RegisterActivity>()
    }

    override fun onError(text: String) {
        toast(text)

    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        initView()
    }

    private fun initView() {
        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)
        mNextBtn.enable(mMobileEt, { isBtnEnable() })
        mNextBtn.enable(mVerifyCodeEt, { isBtnEnable() })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送成功")
            }
            R.id.mNextBtn -> {
                mPresenter.forgetPwd(mMobileEt.text.toString(),mVerifyCodeEt.text.toString())
            }
        }
    }

    fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() && mVerifyCodeEt.text.isNullOrEmpty().not()
    }

}