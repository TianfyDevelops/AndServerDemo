package com.kcst.retrofit

import com.google.gson.Gson
import java.lang.reflect.InvocationTargetException

interface RequestHandler {
    suspend fun <T : BaseResponse<*>> requestHandler(
        retrofitService: RetrofitService,
        baseRequest: BaseRequest,
        responseClazz: Class<T>,
    ): T
}