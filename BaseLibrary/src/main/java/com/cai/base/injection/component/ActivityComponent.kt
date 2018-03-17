package com.cai.base.injection.component

import android.app.Activity
import android.content.Context
import com.cai.base.injection.ActivityScope
import com.cai.base.injection.module.ActivityModule
import com.cai.base.injection.module.AppModule
import com.cai.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component
import javax.inject.Singleton

/**
 * Created by zhengmc on 2018/3/17.
 */

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, LifecycleProviderModule::class))
interface ActivityComponent {

    fun activity(): Activity

    fun context(): Context

    fun lifecycleProvider(): LifecycleProvider<*>

}