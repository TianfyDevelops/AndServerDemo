package com.example.sendserverdemo

import com.kcst.retrofit.BaseResponse

data class UserInfo(val id: Int, val mUserId: String, val mUserName: String) :
    BaseResponse<UserInfo>()
