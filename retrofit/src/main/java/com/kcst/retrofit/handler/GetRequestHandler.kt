package com.kcst.retrofit.handler

import android.accounts.NetworkErrorException
import android.text.TextUtils
import com.kcst.retrofit.GsonUtil
import com.kcst.retrofit.net.RetrofitService
import com.kcst.retrofit.base.BaseRequest
import com.kcst.retrofit.base.BaseResponse
import com.kcst.retrofit.getRawType
import java.lang.NullPointerException
import java.lang.reflect.ParameterizedType
import kotlin.reflect.full.declaredMemberProperties

class GetRequestHandler : RequestHandler {
    override suspend fun <T : BaseResponse<*>> requestHandler(
        retrofitService: RetrofitService,
        baseRequest: BaseRequest,
        responseClazz: Class<T>,
    ): Result<T> {
        if (baseRequest.headers == null) {
            baseRequest.headers = mutableMapOf<String, String>().apply {
                put("Content-Type", "application/x-www-form-urlencoded")
            }
        }
        val requestParams = mutableMapOf<String, String>()
        val requestKClass = baseRequest::class
        requestKClass.declaredMemberProperties.forEach {
            if (!TextUtils.isEmpty(it.call(baseRequest).toString()))
                requestParams[it.name] = it.call(baseRequest).toString()
        }
        try {
            val call =
                retrofitService[baseRequest.headers, baseRequest.getPath(), requestParams]
            val strResponse = call.execute()
            if (strResponse.isSuccessful) {
              val  response =
                    GsonUtil.fromJson<T>(strResponse.body(), responseClazz)
                        ?: return Result.failure(NullPointerException("response is null fail exception"))
                return Result.success(response)
            } else {
                return Result.failure(NetworkErrorException("request fail exception"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }


}