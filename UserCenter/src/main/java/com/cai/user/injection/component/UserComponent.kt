package com.cai.user.injection.component

import com.cai.user.injection.module.UserModule
import com.cai.user.ui.activity.RegisterActivity
import dagger.Component

@Component(modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(activity: RegisterActivity)

}