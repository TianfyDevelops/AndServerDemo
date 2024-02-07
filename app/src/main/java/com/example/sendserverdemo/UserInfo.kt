package com.example.sendserverdemo

import com.kcst.retrofit.BaseResponse


data class UserInfo constructor(
    val id: Int = 1,
    val mUserId: String = "",
    val mUserName: String = ""
) : BaseResponse<UserInfo>()
