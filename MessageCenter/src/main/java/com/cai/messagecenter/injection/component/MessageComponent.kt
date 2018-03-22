package com.cai.messagecenter.injection.component


import com.cai.base.injection.PerComponentScope
import com.cai.base.injection.component.ActivityComponent
import com.cai.messagecenter.injection.module.MessageModule
import com.cai.messagecenter.ui.fragment.MessageFragment
import dagger.Component

/*
    消息模块注入组件
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(MessageModule::class))
interface MessageComponent {
    fun inject(fragment: MessageFragment)
}
