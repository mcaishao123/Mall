package com.cai.base.injection.component

import android.content.Context
import com.cai.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by zhengmc on 2018/3/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context(): Context
}