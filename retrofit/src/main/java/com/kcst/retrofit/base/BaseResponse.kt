package com.kcst.retrofit.base

abstract class BaseResponse<T> {
    var isSuccess = false
    var errorCode = 0
    var errorMsg: String? = null

    abstract var data: T?
    override fun toString(): String {
        return "BaseResponse(isSuccess=$isSuccess, errorCode=$errorCode, errorMsg=$errorMsg, data=$data)"
    }
}