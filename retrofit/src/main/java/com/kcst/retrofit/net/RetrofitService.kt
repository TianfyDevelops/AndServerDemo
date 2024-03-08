package com.kcst.retrofit.net

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RetrofitService {
    @GET("{path}")
    operator fun get(
        @HeaderMap headers: Map<String, String>?,
        @Path(value = "path", encoded = true) path: String,
        @QueryMap map: Map<String, String>
    ): Call<String>

    @FormUrlEncoded
    @POST("{path}")
    fun post(
        @HeaderMap headers: Map<String, String>?,
        @Path(value = "path", encoded = true) path: String,
        @FieldMap map: Map<String, String>
    ): Call<String>
}