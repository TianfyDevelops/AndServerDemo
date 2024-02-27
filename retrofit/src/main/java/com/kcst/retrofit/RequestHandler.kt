package com.kcst.retrofit;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface RequestHandler {
    <T extends BaseResponse> T requestHandler(RetrofitService retrofitService, BaseRequest baseRequest, Class<T> baseResponse, Gson gson) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}
