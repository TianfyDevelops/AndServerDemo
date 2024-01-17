package com.example.sendserverdemo;

public interface RequestHandler {
    <T> BaseResponse<T> requestHandler(RetrofitService retrofitService, BaseRequest baseRequest, BaseResponse<T> baseResponse);
}
