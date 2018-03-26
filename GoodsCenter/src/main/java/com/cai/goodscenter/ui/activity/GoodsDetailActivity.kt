package com.cai.goodscenter.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.cai.base.ext.onClick
import com.cai.base.ui.activity.BaseActivity
import com.cai.base.utils.AppPrefsUtils
import com.cai.goodscenter.R
import com.cai.goodscenter.common.GoodsConstant
import com.cai.goodscenter.event.AddCartEvent
import com.cai.goodscenter.event.UpdateCartSizeEvent
import com.cai.goodscenter.ui.adapter.GoodsDetailVpAdapter
import com.cai.provider.common.afterLogin
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

class GoodsDetailActivity: BaseActivity() {

    private lateinit var mCartBdage: QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        initView()
        initObserve()
        loadCartSize()

    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager,this)

        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }

        mEnterCartTv.onClick {
            startActivity<CartActivity>()
        }

        mLeftIv.onClick {
            finish()
        }

        mCartBdage = QBadgeView(this)
    }

    /*
    加载购物车数量
 */
    private fun loadCartSize() {
        setCartBadge()
    }

    /*
      设置购物车标签
   */
    private fun setCartBadge() {
        mCartBdage.badgeGravity = Gravity.END or Gravity.TOP
        mCartBdage.setGravityOffset(22f,-2f,true)
        mCartBdage.setBadgeTextSize(6f,true)
        mCartBdage.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)

    }

    /*
    监听购物车数量变化
 */
    private fun initObserve(){
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    setCartBadge()
                }.registerInBus(this)
    }

    /*
       Bus取消监听
    */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}