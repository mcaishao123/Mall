package com.cai.goodscenter.data.api

import com.cai.base.data.protocol.BaseResp
import com.cai.goodscenter.data.protocol.AddCartReq
import com.cai.goodscenter.data.protocol.CartGoods
import com.cai.goodscenter.data.protocol.DeleteCartReq
import com.cai.goodscenter.data.protocol.SubmitCartReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    购物车 接口
 */
interface CartApi {
    /*
        获取购物车列表
     */
    @POST("cart/getList")
    fun getCartList(): Observable<BaseResp<MutableList<CartGoods>?>>

    /*
        添加商品到购物车
     */
    @POST("cart/add")
    fun addCart(@Body req: AddCartReq): Observable<BaseResp<Int>>

    /*
        删除购物车商品
     */
    @POST("cart/delete")
    fun deleteCartList(@Body req: DeleteCartReq): Observable<BaseResp<String>>

    /*
        提交购物车商品
     */
    @POST("cart/submit")
    fun submitCart(@Body req: SubmitCartReq): Observable<BaseResp<Int>>
}
