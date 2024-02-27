package com.kcst.retrofit

abstract class BaseRequest<T> {
    @JvmField
    var headers: Map<String, String>? = null
    abstract fun getRequestType(): RequestType
    abstract fun getPath(): String

    abstract fun getRequestData(): T?

    enum class RequestType {
        GET, POST
    }
}