package com.cai.messagecenter.data.api

import com.cai.base.data.protocol.BaseResp
import com.cai.messagecenter.data.protocol.Message
import retrofit2.http.POST
import rx.Observable

/*
    消息 接口
 */
interface MessageApi {

    /*
        获取消息列表
     */
    @POST("msg/getList")
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>>

}
