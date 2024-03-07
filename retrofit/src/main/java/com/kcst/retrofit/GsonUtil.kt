package com.kcst.retrofit

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * @Author tianfy
 * @Date
 *
 */
object GsonUtil {
    private val gson = Gson()
    fun <T> fromJson(json: String, typeOfT: Type): T {
        return gson.fromJson(json, typeOfT)
    }

}