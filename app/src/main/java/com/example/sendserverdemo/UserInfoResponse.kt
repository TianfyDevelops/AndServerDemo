package com.example.sendserverdemo

import com.kcst.retrofit.BaseResponse

/**
 * @Author tianfy
 * @Date
 *
 */
class UserInfoResponse(override var data: UserInfo) : BaseResponse<UserInfo>()