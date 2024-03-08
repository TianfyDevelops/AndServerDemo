package com.example.sendserverdemo

import com.kcst.retrofit.base.BaseRequest

/**
 * @Author tianfy
 * @Date
 *
 */
class UserInfoPostRequest(var mUserId: Int, var mUserName: String) : BaseRequest() {
    override fun getRequestType(): RequestType {
        return RequestType.POST
    }

    override fun getPath(): String {
        return "/user/userInfo"
    }
}