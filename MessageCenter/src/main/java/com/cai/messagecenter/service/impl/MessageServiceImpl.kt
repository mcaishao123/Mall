package com.cai.messagecenter.service.impl

import com.cai.base.ext.convert
import com.cai.messagecenter.data.protocol.Message
import com.cai.messagecenter.data.repository.MessageRepository
import com.cai.messagecenter.service.MessageService
import rx.Observable
import javax.inject.Inject

/*
   消息业务层
 */
class MessageServiceImpl @Inject constructor(): MessageService {

    @Inject
    lateinit var repository: MessageRepository

    /*
        获取消息列表
     */
    override fun getMessageList(): Observable<MutableList<Message>?> {
        return repository.getMessageList().convert()
    }

}
