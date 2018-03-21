package com.cai.user.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.cai.base.common.BaseConstant
import com.cai.base.ext.onClick
import com.cai.base.ui.activity.BaseTakePhotoActivity
import com.cai.base.utils.AppPrefsUtils
import com.cai.base.utils.GlideUtils
import com.cai.provider.common.ProviderConstant
import com.cai.user.R
import com.cai.user.data.protocol.UserInfo
import com.cai.user.injection.component.DaggerUserComponent
import com.cai.user.injection.module.UserModule
import com.cai.user.presenter.UserInfoPresenter
import com.cai.user.presenter.view.UserInfoView
import com.jph.takephoto.model.TResult
import com.cai.user.utils.UserPrefsUtils
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast

class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener {

    private val mUploadManager: UploadManager by lazy { UploadManager() }

    private var mLocalFileUrl: String? = null
    private var mRemoteFileUrl: String? = null

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserMobile: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        initView()
        initData()
    }

    /*
       初始化视图
    */
    private fun initView() {
        mUserIconView.onClick {
            showAlertView()
        }

        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(mRemoteFileUrl!!,
                    mUserNameEt.text?.toString() ?: "",
                    if (mGenderMaleRb.isChecked) "0" else "1",
                    mUserSignEt.text?.toString() ?: ""
            )
        }
    }

    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFileUrl, null, result, { key, info, response ->
            mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")

            Log.d("test", mRemoteFileUrl)
            GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!, mUserIconIv)
        }, null)
    }


    /*
        初始化数据
     */
    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)

        mRemoteFileUrl = mUserIcon
        if (mUserIcon != "") {
            GlideUtils.loadUrlImage(this, mUserIcon!!, mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile

        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)

    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    /*
        获取图片成功回调
     */
    override fun takeSuccess(result: TResult?) {

        mLocalFileUrl = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mUserIconView -> {
                showAlertView()
            }
        }
    }

    override fun takeFail(result: TResult?, msg: String?) {
        super.takeFail(result, msg)

        Log.e("takeFail", "takeFail: msg=" + msg)
    }

    /*
       编辑用户资料回调
    */
    override fun onEditUserResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }


}