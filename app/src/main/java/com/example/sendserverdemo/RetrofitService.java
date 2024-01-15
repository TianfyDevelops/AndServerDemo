package com.example.sendserverdemo;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("{path}")
    Call<String> get(@Path(value = "path" ,encoded = true) String path);
}
