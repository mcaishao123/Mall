package com.cai.user.presenter

import com.cai.base.presenter.BasePresenter
import com.cai.user.presenter.view.UserInfoView
import com.cai.user.service.UserService
import javax.inject.Inject

/*
    重置密码Presenter
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService

//    /*
//        重置密码
//     */
//    fun onGetUploadTokenResult() {
//        if (!checkNetWork()) {
//            return
//        }
//        mView.showLoading()
//
//        userService.resetPwd(mobile, pwd).excute(object : BaseSubscriber<String>(mView) {
//            override fun onNext(t: String) {
//                if (t)
//                    mView.onGetUploadTokenResult()
//            }
//        }, lifecycleProvider)
//
//    }

}