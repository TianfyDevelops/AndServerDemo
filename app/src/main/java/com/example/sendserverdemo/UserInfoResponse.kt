package com.example.sendserverdemo

import com.kcst.retrofit.base.BaseResponse

/**
 * @Author tianfy
 * @Date
 *
 */
class UserInfoResponse constructor(override var data: UserInfo?) : BaseResponse<UserInfo>()