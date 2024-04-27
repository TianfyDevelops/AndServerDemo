package com.example.sendserverdemo.request

import com.kcst.retrofit.base.BaseRequest

/**
 * @Author tianfy
 * @Date
 *
 */
class UserInfoGetRequest(val mUserId: Int) : BaseRequest() {
    override fun getRequestType(): RequestType {
        return RequestType.GET
    }

    override fun getPath(): String {
        return "/user/userInfo"
    }
}