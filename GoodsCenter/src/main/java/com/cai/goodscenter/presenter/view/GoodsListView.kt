package com.cai.goodscenter.presenter.view

import com.cai.base.presenter.view.BaseView
import com.cai.goodscenter.data.protocol.Goods

/*
    商品列表 视图回调
 */
interface GoodsListView : BaseView {

    //获取商品列表
    fun onGetGoodsListResult(result: MutableList<Goods>?)
}
