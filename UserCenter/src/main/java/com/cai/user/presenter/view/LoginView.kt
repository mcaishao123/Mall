package com.cai.user.presenter.view

import com.cai.base.presenter.view.BaseView
import com.cai.user.data.protocol.UserInfo

/**
 * Created by zhengmc on 2018/3/19.
 */
interface LoginView: BaseView {

    fun onLoginResult(result: UserInfo)

}