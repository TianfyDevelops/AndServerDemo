package com.example.sendserverdemo;


import org.jetbrains.annotations.Nullable;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RetrofitService {

    @GET("{path}")
    Call<String> get(@HeaderMap Map<String, String> headers, @Path(value = "path", encoded = true) String path, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("{path}")
    Call<String> post(@Path(value = "path", encoded = true) String path, @FieldMap Map<String, String> map);

}
