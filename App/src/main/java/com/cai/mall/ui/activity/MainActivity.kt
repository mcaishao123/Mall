package com.cai.mall.ui.activity

import android.os.Bundle
import com.cai.base.ui.activity.BaseActivity
import com.cai.mall.R
import com.cai.mall.ui.fragment.HomeFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Example of a call to a native method
        intiView()

    }

    private fun intiView() {
        val homeFragment = HomeFragment();

    }


}
