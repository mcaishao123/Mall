package com.cai.user.presenter.view

import com.cai.base.presenter.view.BaseView
import com.cai.user.data.protocol.UserInfo

/*
    注册
 */
interface UserInfoView : BaseView {

    /*
           获取上传凭证回调
        */
    fun onGetUploadTokenResult(result:String)

    /*
        编辑用户资料回调
     */
    fun onEditUserResult(result: UserInfo)
}