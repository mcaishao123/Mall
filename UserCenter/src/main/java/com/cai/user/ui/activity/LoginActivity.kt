package com.cai.user.ui.activity

import android.os.Bundle
import android.view.View
import com.cai.base.ext.enable
import com.cai.base.ext.onClick
import com.cai.base.ui.activity.BaseMvpActivity
import com.cai.user.R
import com.cai.user.data.protocol.UserInfo
import com.cai.user.injection.component.DaggerUserComponent
import com.cai.user.injection.module.UserModule
import com.cai.user.presenter.LoginPresenter
import com.cai.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by zhengmc on 2018/3/19.
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        mLoginBtn.onClick(this)
        mHeaderBar.getRightView().onClick(this)

        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mPwdEt, { isBtnEnable() })

    }


    override fun onLoginResult(result: UserInfo) {
        toast(result.id)
    }

    override fun onError(text: String) {
        toast(text)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.mLoginBtn->{
                mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), "")
            }
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }

        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not()
                && mPwdEt.text.isNullOrEmpty().not()
    }

}