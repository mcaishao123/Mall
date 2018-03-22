package com.cai.goodscenter.injection.component

import com.cai.base.injection.PerComponentScope
import com.cai.base.injection.component.ActivityComponent
import com.cai.goodscenter.injection.module.CartModule
import com.cai.goodscenter.ui.fragment.CartFragment
import dagger.Component

/*
    购物车Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CartModule::class))
interface CartComponent {
    fun inject(fragment: CartFragment)
}
