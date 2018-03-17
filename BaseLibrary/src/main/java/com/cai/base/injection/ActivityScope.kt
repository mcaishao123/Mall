package com.cai.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME


/**
 * Created by zhengmc on 2018/3/17.
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope