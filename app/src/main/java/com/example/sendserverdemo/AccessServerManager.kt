package com.example.sendserverdemo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccessServerManager private constructor() {
    private val retrofitService: RetrofitService =
        RetrofitManager.getRetrofit().create(RetrofitService::class.java)

    suspend fun <ResponseT> request(
        baseRequest: BaseRequest,
        responseTClass: Class<ResponseT>?
    ): ResponseT {
        val requestHandler: RequestHandler = when (baseRequest.requestType) {
            BaseRequest.RequestType.GET -> GetRequestHandler()
            BaseRequest.RequestType.POST -> PostRequestHandler()
        }
        return withContext(Dispatchers.IO) {
            requestHandler.requestHandler(retrofitService, baseRequest, responseTClass)
        }
    }

    companion object {
        @Volatile
        private lateinit var INSTANCE: AccessServerManager
        val instance: AccessServerManager
            get() {
                if (INSTANCE == null) {
                    synchronized(AccessServerManager::class.java) {
                        if (INSTANCE == null) {
                            INSTANCE = AccessServerManager()
                        }
                    }
                }
                return INSTANCE
            }
    }
}