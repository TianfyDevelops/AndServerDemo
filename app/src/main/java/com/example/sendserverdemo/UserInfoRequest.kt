package com.example.sendserverdemo

import com.kcst.retrofit.BaseRequest

/**
 * @Author tianfy
 * @Date
 *
 */
class UserInfoRequest : BaseRequest<UserInfoParams>() {
    override fun getRequestType(): RequestType {
        return RequestType.GET
    }

    override fun getPath(): String {
        return "/user/userInfos"
    }

    override fun getRequestData(): UserInfoParams? {
        return null
    }
}