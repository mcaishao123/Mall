package com.cai.goodscenter.presenter.view

import com.cai.base.presenter.view.BaseView
import com.cai.goodscenter.data.protocol.CartGoods

/*
    购物车页面 视图回调
 */
interface CartListView : BaseView {

    //获取购物车列表
    fun onGetCartListResult(result: MutableList<CartGoods>?)
    //删除购物车
    fun onDeleteCartListResult(result: Boolean)
    //提交购物车
    fun onSubmitCartListResult(result: Int)
}
