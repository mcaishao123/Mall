package com.cai.user.ui.activity

import android.os.Bundle
import android.view.View
import com.cai.base.ext.onClick
import com.cai.base.ui.activity.BaseTakePhotoActivity
import com.cai.user.R
import com.cai.user.data.protocol.UserInfo
import com.cai.user.presenter.UserInfoPresenter
import com.cai.user.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity:BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView,View.OnClickListener {

    private var mLocalFileUrl:String? = null
    private var mRemoteFileUrl:String? = null

    private var mUserIcon:String? = null
    private var mUserName:String? = null
    private var mUserMobile:String? = null
    private var mUserGender:String? = null
    private var mUserSign:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        initView()
    }

    private fun initView() {
        mUserIconView.onClick (this)

    }

    override fun onGetUploadTokenResult(result: String) {

    }

    override fun onEditUserResult(result: UserInfo) {

    }

    override fun injectComponent() {

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.mUserIconView->{
                showAlertView()
            }
        }
    }



}