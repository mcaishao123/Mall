package com.cai.base.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.cai.base.common.BaseApplication
import com.cai.base.injection.component.ActivityComponent
import com.cai.base.injection.component.DaggerActivityComponent
import com.cai.base.injection.module.ActivityModule
import com.cai.base.injection.module.LifecycleProviderModule
import com.cai.base.presenter.BasePresenter
import com.cai.base.presenter.view.BaseView
import com.cai.base.utils.DateUtils
import com.cai.base.widgets.ProgressLoading
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import org.jetbrains.anko.toast
import java.io.File
import javax.inject.Inject


/*
    存在选择图片的Activity基础封装
 */
abstract open class BaseTakePhotoActivity<T : BasePresenter<*>> : BaseActivity(), BaseView, TakePhoto.TakeResultListener {
    private val REQUEST_CODE_ASK_CAMERA = 123

    private lateinit var mTakePhoto:TakePhoto

    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: ActivityComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()

        mTakePhoto = TakePhotoImpl(this,this)
        mTakePhoto.onCreate(savedInstanceState)

        mLoadingDialog = ProgressLoading.create(this)
        ARouter.getInstance().inject(this)
    }

    /*
        Dagger注册
     */
    protected abstract fun injectComponent()

    /*
        初始化Activity Component
     */
    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()

    }

    /*
        显示加载框，默认实现
     */
    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    /*
        隐藏加载框，默认实现
     */
    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    /*
        错误信息提示，默认实现
     */
    override fun onError(text:String) {
        toast(text)
    }

    /*
        弹出选择框，默认实现
        可根据实际情况，自行修改
     */
    protected fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, OnItemClickListener { o, position ->
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),false)
            when (position) {
                0 -> {
                    requestPermissions()
                }
                1 -> mTakePhoto.onPickFromGallery()
            }
        }

        ).show()

    }

    /*
        获取图片，成功回调
     */
    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto",result?.image?.originalPath)
        Log.d("TakePhoto",result?.image?.compressPath)
    }

    /*
        获取图片，取消回调
     */
    override fun takeCancel() {
    }

    /*
        获取图片，失败回调
     */
    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("takePhoto",msg)
    }

    /*
        TakePhoto默认实现
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode,resultCode,data)
    }

    /*
        新建临时文件
     */
    fun createTempFile(): File {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()){
            return File(Environment.getExternalStorageDirectory(), tempFileName)

        }

        return File(filesDir, tempFileName)

    }


    fun requestPermissions() {
        val checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "shouldShowRequestPermissionRationale", Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("应用需要开启拍照的权限，是否继续？")
                        .setPositiveButton("确定", { dialog, which -> ActivityCompat.requestPermissions(this@BaseTakePhotoActivity, arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_ASK_CAMERA) })
                        .setNegativeButton("取消", null).show()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_ASK_CAMERA)
            }

        } else {
            mTakePhoto.onPickFromCapture(Uri.fromFile(createTempFile()))
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {


        when (requestCode) {
            REQUEST_CODE_ASK_CAMERA -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted 授予权限
                mTakePhoto.onPickFromCapture(Uri.fromFile(createTempFile()))
            } else {
                // Permission Denied 权限被拒绝
                Toast.makeText(this@BaseTakePhotoActivity, "Permission Denied",
                        Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
