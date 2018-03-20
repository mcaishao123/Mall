package com.cai.user.data.protocol

/**
 * Created by zhengmc on 2018/3/19.
 */

/*
    用户实体类
 */
data class UserInfo(val id:String,
                    val userIcon:String,
                    val userName:String,
                    val userGender:String,
                    val userMobile:String,
                    val userSign:String)
