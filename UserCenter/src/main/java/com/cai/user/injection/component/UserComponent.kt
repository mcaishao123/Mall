package com.cai.user.injection.component

import com.cai.base.injection.PerComponentScope
import com.cai.base.injection.component.ActivityComponent
import com.cai.user.injection.module.UserModule
import com.cai.user.ui.activity.RegisterActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(activity: RegisterActivity)

}