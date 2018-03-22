package com.cai.goodscenter.injection.component

import com.cai.base.injection.PerComponentScope
import com.cai.base.injection.component.ActivityComponent
import com.cai.goodscenter.injection.module.CartModule
import com.cai.goodscenter.injection.module.GoodsModule
import com.cai.goodscenter.ui.activity.GoodsActivity
import com.cai.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/*
    商品Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(GoodsModule::class, CartModule::class))
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
}
