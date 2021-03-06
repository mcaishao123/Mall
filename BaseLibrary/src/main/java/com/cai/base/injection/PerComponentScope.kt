package com.cai.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by zhengmc on 2018/3/17.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class PerComponentScope