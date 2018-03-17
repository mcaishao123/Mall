package com.cai.base.common

import android.app.Application
import com.cai.base.injection.component.AppComponent
import com.cai.base.injection.component.DaggerAppComponent
import com.cai.base.injection.module.AppModule

/**
 * Created by zhengmc on 2018/3/17.
 */
class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppInject()
    }

    fun initAppInject() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


}