package com.example.sendserverdemo;

import com.kcst.sendserver.model.UserInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/user/userInfo")
    Call<UserInfo> getUserInfo();
}
