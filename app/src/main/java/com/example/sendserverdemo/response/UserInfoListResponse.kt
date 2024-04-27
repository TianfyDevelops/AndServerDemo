package com.example.sendserverdemo.response

import com.kcst.retrofit.base.BaseResponse

/**
 * @Author tianfy
 * @Date
 *
 */
class UserInfoListResponse(override var data: List<UserInfo>?) : BaseResponse<List<UserInfo>>()