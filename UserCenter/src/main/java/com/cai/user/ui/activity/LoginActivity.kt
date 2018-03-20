package com.cai.user.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.cai.base.ui.activity.BaseMvpActivity
import com.cai.user.R
import com.cai.user.data.protocol.UserInfo
import com.cai.user.presenter.LoginPresenter
import com.cai.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

/**
 * Created by zhengmc on 2018/3/19.
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {
    override fun onClick(v: View) {
        when(v.id){
            R.id.mLoginBtn->{
                mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString(),)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    override fun onLoginResult(result: UserInfo) {

    }

    override fun injectComponent() {

    }

}