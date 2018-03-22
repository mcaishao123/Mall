package com.cai.goodscenter.presenter.view

import com.cai.base.presenter.view.BaseView
import com.cai.goodscenter.data.protocol.Category

/*
    商品分类 视图回调
 */
interface CategoryView : BaseView {

    //获取商品分类列表
    fun onGetCategoryResult(result: MutableList<Category>?)
}
