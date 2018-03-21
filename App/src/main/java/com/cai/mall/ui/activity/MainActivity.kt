package com.cai.mall.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.cai.base.common.AppManager
import com.cai.base.ui.activity.BaseActivity
import com.cai.mall.R
import com.cai.mall.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : BaseActivity() {
    private var preTime: Long = 0
    //Fragment 栈管理
    private val mStack = Stack<Fragment>()
    //主界面Fragment
    private val mHomeFragment by lazy { HomeFragment() }
    //商品分类Fragment
    private val mCategoryFragment by lazy { HomeFragment() }
    //购物车Fragment
    private val mCartFragment by lazy { HomeFragment() }
    //消息Fragment
    private val mMsgFragment by lazy { HomeFragment() }
    //"我的"Fragment
    private val mMeFragment by lazy { HomeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Example of a call to a native method
        initFragment()
        initBottomNav()
        changeFragment(0)
//        initObserve()
//        loadCartSize()
    }

    /*
            初始化Fragment栈管理
         */
    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContainer,mHomeFragment)
        manager.add(R.id.mContainer,mCategoryFragment)
        manager.add(R.id.mContainer,mCartFragment)
        manager.add(R.id.mContainer,mMsgFragment)
        manager.add(R.id.mContainer,mMeFragment)
        manager.commit()

        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)

    }

    /*
        初始化底部导航切换事件
     */
    private fun initBottomNav(){
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })

        mBottomNavBar.checkMsgBadge(false)
    }
    /*
           切换Tab，切换对应的Fragment
        */
    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack){
            manager.hide(fragment)
        }

        manager.show(mStack[position])
        manager.commit()
    }

    /*
       重写Back事件，双击退出
    */
    override fun onBackPressed() {
        var time = System.currentTimeMillis()
        if(time - preTime > 20000){
            preTime = time
            toast("再按一次退出程序")
        }else{
            AppManager.instance.exitApp(this)
        }
    }
}
