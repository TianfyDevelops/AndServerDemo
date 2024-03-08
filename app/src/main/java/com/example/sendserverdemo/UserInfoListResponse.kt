package com.example.sendserverdemo

import com.kcst.retrofit.base.BaseResponse

/**
 * @Author tianfy
 * @Date
 *
 */
class UserInfoListResponse(override var data: List<UserInfo>?) : BaseResponse<List<UserInfo>>()