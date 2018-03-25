package com.cai.goodscenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cai.base.ext.setVisible
import com.cai.base.ext.startLoading
import com.cai.base.ui.adapter.BaseRecyclerViewAdapter
import com.cai.base.ui.fragment.BaseMvpFragment
import com.cai.goodscenter.R
import com.cai.goodscenter.common.GoodsConstant
import com.cai.goodscenter.data.protocol.Category
import com.cai.goodscenter.injection.component.DaggerCategoryComponent
import com.cai.goodscenter.injection.module.CategoryModule
import com.cai.goodscenter.presenter.CategoryPresenter
import com.cai.goodscenter.presenter.view.CategoryView
import com.cai.goodscenter.ui.activity.GoodsActivity
import com.cai.goodscenter.ui.adapter.SecondCategoryAdapter
import com.cai.goodscenter.ui.adapter.TopCategoryAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity

class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {
    //一级分类Adapter
    lateinit var topAdapter: TopCategoryAdapter
    //二级分类Adapter
    lateinit var secondAdapter: SecondCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData(0)

    }

    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(context)
        mTopCategoryRv.adapter = topAdapter
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category>{
            override fun onItemClick(item: Category, position: Int) {
                for(category in topAdapter.dataList){
                    category.isSelected = category.id == item.id
                }
                topAdapter.notifyDataSetChanged()
                loadData(item.id)
            }

        })
        mSecondCategoryRv.layoutManager = GridLayoutManager(context,3)
        secondAdapter = SecondCategoryAdapter(context)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                startActivity<GoodsActivity>(GoodsConstant.KEY_CATEGORY_ID  to item.id)
            }
        })
    }


    private fun loadData(id: Int) {
        if(id != 0){
            mMultiStateView.startLoading()
        }
        mPresenter.getCategory(id)

    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            } else {
                secondAdapter.setData(result)
                mTopCategoryIv.setVisible(true)
                mCategoryTitleTv.setVisible(true)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            //没有数据
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(activityComponent).categoryModule(CategoryModule()).build().inject(this)
        mPresenter.mView = this
    }

}