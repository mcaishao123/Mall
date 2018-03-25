package com.cai.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cai.base.ui.adapter.BaseRecyclerViewAdapter
import com.cai.goodscenter.R
import com.cai.goodscenter.data.protocol.Category
import kotlinx.android.synthetic.main.item_top_category.view.*

class TopCategoryAdapter(context: Context) : BaseRecyclerViewAdapter<Category, TopCategoryAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
       val view =  LayoutInflater.from(mContext).inflate(R.layout.item_top_category,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>?) {
        super.onBindViewHolder(holder, position, payloads)
        val model = dataList[position]
        holder.itemView.mTopCategoryNameTv.text = model.categoryName
        holder.itemView.mTopCategoryNameTv.isSelected = model.isSelected
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}