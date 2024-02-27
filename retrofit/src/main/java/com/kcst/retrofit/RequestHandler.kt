package com.kcst.retrofit

import com.google.gson.Gson
import java.lang.reflect.InvocationTargetException

interface RequestHandler {
    fun <R,T> requestHandler(
        retrofitService: RetrofitService,
        baseRequest: BaseRequest<R>,
        baseResponse: BaseResponse<T>,
        gson: Gson?
    ): T?
}