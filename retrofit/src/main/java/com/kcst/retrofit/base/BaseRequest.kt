package com.kcst.retrofit.base

abstract class BaseRequest {
    var headers: Map<String, String>? = null
    abstract fun getRequestType(): RequestType
    abstract fun getPath(): String
    enum class RequestType {
        GET, POST
    }
}