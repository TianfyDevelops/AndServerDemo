package com.example.sendserverdemo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import retrofit2.Call;

public class GetRequestHandler implements RequestHandler{
    @Override
    public <T> BaseResponse<T> requestHandler(RetrofitService retrofitService, BaseRequest baseRequest, BaseResponse<T> baseResponse) {
        return null;
    }

//    @Override
//    public <T> BaseResponse<T>  requestHandler(RetrofitService retrofitService, BaseRequest baseRequest, Class<BaseResponse<T>> baseResponse) {
//        Call<String> stringCall = retrofitService.get(baseRequest.headers, baseRequest.path, baseRequest.requestParams);
//
//
//        new Gson().fromJson(stringCall);
//
//
//
//
//        return null;
//    }
}
