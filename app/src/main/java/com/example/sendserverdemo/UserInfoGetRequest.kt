package com.example.sendserverdemo

import com.kcst.retrofit.BaseRequest

/**
 * @Author tianfy
 * @Date
 *
 */
class UserInfoGetRequest : BaseRequest() {
    override fun getRequestType(): RequestType {
        return RequestType.GET
    }

    override fun getPath(): String {
        return "/user/userInfo"
    }
}