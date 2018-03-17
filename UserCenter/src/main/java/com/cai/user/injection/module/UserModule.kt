package com.cai.user.injection.module

import com.cai.base.injection.PerComponentScope
import com.cai.user.service.UserService
import com.cai.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun provideUserService(userService: UserServiceImpl): UserService {
        return userService
    }
}