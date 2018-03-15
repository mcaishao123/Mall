package com.cai.base.ui.activity

import com.cai.base.presenter.BasePresenter
import com.cai.base.presenter.view.BaseView

open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var mPresenter: T

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(text: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}