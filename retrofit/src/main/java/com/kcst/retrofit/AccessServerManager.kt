package com.kcst.retrofit

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.NotNull

class AccessServerManager private constructor() {

    private val retrofitService: RetrofitService =
        RetrofitManager.getInstance().retrofit.create(RetrofitService::class.java)

    private val gson = Gson()

    suspend fun <T> request(
        baseRequest: BaseRequest,
        responseTClass: Class<T>
    ): T {
        val requestHandler: RequestHandler = when (baseRequest.requestType) {
            BaseRequest.RequestType.GET -> GetRequestHandler()
            BaseRequest.RequestType.POST -> PostRequestHandler()
        }
        return withContext(Dispatchers.IO) {
            requestHandler.requestHandler(retrofitService, baseRequest, responseTClass, gson)
        }
    }

    companion object {
        val INSTANCE: AccessServerManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { AccessServerManager() }
    }
}