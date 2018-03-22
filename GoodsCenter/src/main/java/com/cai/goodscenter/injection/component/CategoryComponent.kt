package com.cai.goodscenter.injection.component

import com.cai.base.injection.PerComponentScope
import com.cai.base.injection.component.ActivityComponent
import com.cai.goodscenter.injection.module.CategoryModule
import com.cai.goodscenter.ui.fragment.CategoryFragment
import dagger.Component

/*
    商品分类Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}
