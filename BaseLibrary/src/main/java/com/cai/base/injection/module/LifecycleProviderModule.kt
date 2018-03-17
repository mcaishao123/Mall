package com.cai.base.injection.module

import android.content.Context
import com.cai.base.common.BaseApplication
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by zhengmc on 2018/3/17.
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun provideLifecycleProvider():  LifecycleProvider<*> {
        return lifecycleProvider
    }
}