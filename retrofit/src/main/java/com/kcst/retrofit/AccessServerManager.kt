package com.kcst.retrofit

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccessServerManager private constructor() {

    private val retrofitService: RetrofitService =
        RetrofitManager.INSTANCE.retrofit.create(RetrofitService::class.java)

    private val gson = Gson()

    suspend fun <T : BaseResponse<*>> request(
        baseRequest: BaseRequest,
        responseClazz: Class<T>,
        ): T {
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