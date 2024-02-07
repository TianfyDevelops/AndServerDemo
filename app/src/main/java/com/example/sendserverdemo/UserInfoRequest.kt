package com.example.sendserverdemo

import com.kcst.retrofit.AccessServerManager
import com.kcst.retrofit.BaseRequest
import com.kcst.retrofit.BaseResponse
import com.kcst.retrofit.Result

/**
 * @Author tianfy
 * @Date
 *
 */
class UserInfoRequest : BaseRequest<UserInfo>() {


    suspend fun requestPost(): Result<UserInfoResponse> {
        val userInfo = UserInfo(id = 1, mUserId = "123", mUserName = "123")
        setData(userInfo)
        setPath("/user/userInfo")
        setRequestType(RequestType.POST)
        return AccessServerManager.INSTANCE.request(
            this,
            UserInfoResponse::class.java
        )
    }

    suspend fun requestGet(): Result<UserInfoResponse> {
        setPath("/user/userInfo")
        setRequestType(RequestType.GET)
        return AccessServerManager.INSTANCE.request(
            this,
            UserInfoResponse::class.java
        )
    }
}