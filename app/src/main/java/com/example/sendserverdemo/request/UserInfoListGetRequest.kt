package com.example.sendserverdemo.request

import com.kcst.retrofit.base.BaseRequest

/**
 * @Author tianfy
 * @Date
 *
 */
class UserInfoListGetRequest() : BaseRequest() {
    override fun getRequestType(): RequestType {
        return RequestType.GET
    }

    override fun getPath(): String {
        return "/user/userInfos"
    }
}