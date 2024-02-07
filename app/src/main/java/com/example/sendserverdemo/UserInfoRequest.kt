package com.example.sendserverdemo

import com.kcst.retrofit.AccessServerManager
import com.kcst.retrofit.BaseRequest
import com.kcst.retrofit.Result

/**
 * @Author tianfy
 * @Date
 *
 */
class UserInfoRequest : BaseRequest<UserInfoParams>() {


    suspend fun requestPost(): Result<UserInfo> {
        val userInfo = UserInfoParams(id = 1, mUserId = "123", mUserName = "123")
        setData(userInfo)
        setPath("/user/userInfo")
        setRequestType(RequestType.POST)
        return AccessServerManager.INSTANCE.request(
            this,
            UserInfo::class.java
        )
    }

    suspend fun requestGet(): Result<UserInfo> {
        setPath("/user/userInfo")
        setRequestType(RequestType.GET)
        return AccessServerManager.INSTANCE.request(
            this,
            UserInfo::class.java
        )
    }
}