package com.example.sendserverdemo.repository

import com.example.sendserverdemo.request.UserInfoGetRequest
import com.example.sendserverdemo.request.UserInfoListGetRequest
import com.example.sendserverdemo.request.UserInfoPostRequest
import com.example.sendserverdemo.response.UserInfoListResponse
import com.example.sendserverdemo.response.UserInfoResponse
import com.kcst.retrofit.net.AccessServerManager

class MainRepository {
    suspend fun getUserInfo(): Result<UserInfoResponse> {
        val userInfoRequest = UserInfoGetRequest(1)
        return AccessServerManager.INSTANCE.request(userInfoRequest, UserInfoResponse::class.java)
    }

    suspend fun getUserInfoList(): Result<UserInfoListResponse> {
        val userInfoListGetRequest = UserInfoListGetRequest()
        return AccessServerManager.INSTANCE.request(
            userInfoListGetRequest,
            UserInfoListResponse::class.java
        )
    }

    suspend fun setUserInfo(): Result<UserInfoResponse> {
        val userInfoRequest = UserInfoPostRequest(1, "xiaoming")
        return AccessServerManager.INSTANCE.request(userInfoRequest, UserInfoResponse::class.java)
    }


}