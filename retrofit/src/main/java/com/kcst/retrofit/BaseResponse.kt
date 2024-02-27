package com.kcst.retrofit

open abstract class BaseResponse<T> {
    @JvmField
    var isSuccess = false
    @JvmField
    var errorCode = 0
    @JvmField
    var errorMsg: String? = null

    abstract var data: T
    override fun toString(): String {
        return "BaseResponse(isSuccess=$isSuccess, errorCode=$errorCode, errorMsg=$errorMsg, data=$data)"
    }


}