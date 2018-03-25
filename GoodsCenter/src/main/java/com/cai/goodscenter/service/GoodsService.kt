package com.cai.goodscenter.service

import com.cai.goodscenter.data.protocol.Goods
import rx.Observable

/*
    商品 业务层 接口
 */
interface GoodsService {

    /*
        获取商品列表
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>

    /*
        根据关键字查询商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>

    /*
        获取商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<Goods>
}