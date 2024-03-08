package com.kcst.retrofit.handler

import com.kcst.retrofit.GsonUtil
import com.kcst.retrofit.net.RetrofitService
import com.kcst.retrofit.base.BaseRequest
import com.kcst.retrofit.base.BaseResponse
import com.kcst.retrofit.getRawType
import java.lang.reflect.ParameterizedType
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

class PostRequestHandler : RequestHandler {
    override suspend fun <T : BaseResponse<*>> requestHandler(
        retrofitService: RetrofitService,
        baseRequest: BaseRequest,
        responseClazz: Class<T>,
    ): T {
        if (baseRequest.headers == null) {
            baseRequest.headers = mutableMapOf<String, String>().apply {
                put("Content-Type", "application/x-www-form-urlencoded")
            }
        }
        val requestParams = mutableMapOf<String, String>()
        val requestKClass = baseRequest::class
        requestKClass.declaredMemberProperties.forEach {
            it.isAccessible = true
            requestParams[it.name] = it.call(baseRequest).toString()
        }
        val genericSuperclass = responseClazz.genericSuperclass
        val parameterizedType = genericSuperclass as ParameterizedType
        val type = parameterizedType.actualTypeArguments[0]
        val constructor = responseClazz.getDeclaredConstructor(getRawType(type))
        constructor.isAccessible = true
        var response: T = constructor.newInstance(null)
        try {
            val call =
                retrofitService.post(baseRequest.headers, baseRequest.getPath(), requestParams)
            val strResponse = call.execute()
            if (strResponse.isSuccessful) {
                response =
                    GsonUtil.fromJson(strResponse.body()!!, responseClazz)
            } else {
                response.isSuccess = false
                response.errorCode = strResponse.code()
                response.errorMsg = strResponse.message()
            }
        } catch (e: Exception) {
            response.isSuccess = false
            response.errorCode = 501
            response.errorMsg = e.message
        }
        return response
    }

}