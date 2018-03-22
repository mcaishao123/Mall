package com.cai.messagecenter.data.repository


import com.cai.base.data.net.RetrofitFactory
import com.cai.base.data.protocol.BaseResp
import com.cai.messagecenter.data.api.MessageApi
import com.cai.messagecenter.data.protocol.Message
import rx.Observable
import javax.inject.Inject


/*
   消息数据层
 */
class MessageRepository @Inject constructor() {

    /*
        获取消息列表
     */
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>> {
        return RetrofitFactory.instance.create(MessageApi::class.java).getMessageList()
    }



}
