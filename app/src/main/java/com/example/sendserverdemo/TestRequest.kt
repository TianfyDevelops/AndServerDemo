package com.example.sendserverdemo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class TestRequest {
    fun <T> request(retrofit: Retrofit, responseClazz: Class<T>): T? {

        val testRetrofitService = retrofit.create(TestRetrofitService::class.java)
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch{
            val response = testRetrofitService.userInfo.execute()
            if (response.isSuccessful){

                val typeParameters = responseClazz.typeParameters



            }
        }




        return null
    }
}