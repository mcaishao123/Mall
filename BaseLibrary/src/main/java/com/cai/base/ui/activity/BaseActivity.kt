package com.cai.base.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

open class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}