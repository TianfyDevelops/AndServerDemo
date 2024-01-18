package com.example.sendserverdemo;



import retrofit2.Call;
import retrofit2.http.GET;

public interface TestRetrofitService {
    @GET("/user/userInfo")
    Call<String> getUserInfo();

}
