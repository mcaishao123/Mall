package com.cai.base.data.protocol

data class BaseResp<out T>(val status:Int, val message: String, val data: T)



