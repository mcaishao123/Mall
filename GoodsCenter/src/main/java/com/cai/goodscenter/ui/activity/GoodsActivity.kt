package com.cai.goodscenter.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.cai.base.ui.activity.BaseMvpActivity
import com.cai.goodscenter.R
import com.cai.goodscenter.common.GoodsConstant
import com.cai.goodscenter.data.protocol.Goods
import com.cai.goodscenter.injection.component.DaggerGoodsComponent
import com.cai.goodscenter.injection.module.GoodsModule
import com.cai.goodscenter.presenter.GoodsListPresenter
import com.cai.goodscenter.presenter.view.GoodsListView
import com.cai.goodscenter.ui.adapter.GoodsAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_goods.*

class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView, BGARefreshLayout.BGARefreshLayoutDelegate {

    lateinit var mGoodsAdapter: GoodsAdapter
    private var mCurrentPage = 1
    private var mMaxPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        initRefreshLayout()
        loadData()
    }

    private fun initView() {
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsRv.adapter = mGoodsAdapter

    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun loadData() {
        mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, -1), mMaxPage)

    }
    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null && result.size > 0) {
            mMaxPage = result[0].maxPage
            if (mCurrentPage == 1) {
                mGoodsAdapter.setData(result)
            } else {
                mGoodsAdapter.dataList.addAll(result)
                mGoodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mCurrentPage < mMaxPage) {
            mCurrentPage++
            loadData()
            true
        } else {
            false
        }
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }


}