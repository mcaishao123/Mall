package com.cai.base.injection.module

import android.content.Context
import com.cai.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by zhengmc on 2018/3/17.
 */
@Module
class AppModule(private val context: BaseApplication) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }
}