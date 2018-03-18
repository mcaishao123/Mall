package com.cai.base.ui.activity

import android.os.Bundle
import com.cai.base.common.BaseApplication
import com.cai.base.injection.component.ActivityComponent
import com.cai.base.injection.component.DaggerActivityComponent
import com.cai.base.injection.module.ActivityModule
import com.cai.base.injection.module.LifecycleProviderModule
import com.cai.base.presenter.BasePresenter
import com.cai.base.presenter.view.BaseView
import com.cai.base.wigets.ProgressLoading
import javax.inject.Inject

open abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    lateinit var mLoadingDialog: ProgressLoading

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text: String) {
    }

    abstract fun injectComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInject()
        injectComponent()
        mLoadingDialog = ProgressLoading.create(this)

    }

    private fun initActivityInject() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .activityModule(ActivityModule(this))
                .build()
    }
}