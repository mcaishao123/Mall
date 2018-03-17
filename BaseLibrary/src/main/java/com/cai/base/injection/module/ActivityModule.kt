package com.cai.base.injection.module

import android.app.Activity
import com.cai.base.injection.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by zhengmc on 2018/3/17.
 */

@Module
class ActivityModule(private val activity: Activity){

    @ActivityScope
    @Provides
    fun provideActivity():Activity{
        return activity
    }
}