package com.kcst.retrofit.net

import com.kcst.retrofit.base.BaseRequest
import com.kcst.retrofit.base.BaseResponse
import com.kcst.retrofit.handler.GetRequestHandler
import com.kcst.retrofit.handler.PostRequestHandler
import com.kcst.retrofit.handler.RequestHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccessServerManager private constructor() {

    private val retrofitService: RetrofitService =
        RetrofitManager.INSTANCE.retrofit.create(RetrofitService::class.java)

    suspend fun <T : BaseResponse<*>> request(
        baseRequest: BaseRequest,
        responseClazz: Class<T>,
        ): Result<T> {
        val requestHandler: RequestHandler = when (baseRequest.getRequestType()) {
            BaseRequest.RequestType.GET -> GetRequestHandler()
            BaseRequest.RequestType.POST -> PostRequestHandler()
        }
        return withContext(Dispatchers.IO) {
            requestHandler.requestHandler(retrofitService, baseRequest, responseClazz)
        }
    }

    companion object {
        val INSTANCE: AccessServerManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { AccessServerManager() }
    }
}