package com.cai.messagecenter.service

import com.cai.messagecenter.data.protocol.Message
import rx.Observable

/*
   消息业务接口
 */
interface MessageService {
    //获取消息列表
    fun getMessageList(): Observable<MutableList<Message>?>

}
