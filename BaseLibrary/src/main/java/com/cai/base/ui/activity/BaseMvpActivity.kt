package com.cai.base.ui.activity

import com.cai.base.presenter.BasePresenter
import com.cai.base.presenter.view.BaseView
import javax.inject.Inject

open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text: String) {
    }

}