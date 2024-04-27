package com.kcst.retrofit.handler

import com.kcst.retrofit.net.RetrofitService
import com.kcst.retrofit.base.BaseRequest
import com.kcst.retrofit.base.BaseResponse

interface RequestHandler {
    suspend fun <T : BaseResponse<*>> requestHandler(
        retrofitService: RetrofitService,
        baseRequest: BaseRequest,
        responseClazz: Class<T>,
    ): Result<T>
}