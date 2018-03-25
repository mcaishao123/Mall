package com.cai.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cai.base.ext.loadUrl
import com.cai.base.ui.adapter.BaseRecyclerViewAdapter
import com.cai.base.utils.YuanFenConverter
import com.cai.goodscenter.R
import com.cai.goodscenter.data.protocol.Goods
import kotlinx.android.synthetic.main.item_goods.view.*

class GoodsAdapter(context: Context): BaseRecyclerViewAdapter<Goods, GoodsAdapter.ViewHolder>(context) {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_goods,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //商品图标
        holder.itemView.mGoodsIconIv.loadUrl(model.goodsDefaultIcon)
        //商品描述
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        //商品价格
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsDefaultPrice)
        //商品销量及库存
        holder.itemView.mGoodsSalesStockTv.text = "销量${model.goodsSalesCount}件     库存${model.goodsStockCount}"
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}