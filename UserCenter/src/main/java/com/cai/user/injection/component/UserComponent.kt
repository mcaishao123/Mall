package com.cai.user.injection.component

import com.cai.base.injection.PerComponentScope
import com.cai.base.injection.component.ActivityComponent
import com.cai.user.injection.module.UploadModule
import com.cai.user.injection.module.UserModule
import com.cai.user.ui.activity.*
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(UserModule::class, UploadModule::class))
interface UserComponent {

    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)

}