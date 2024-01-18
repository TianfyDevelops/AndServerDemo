package com.kcst.retrofit;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;

public interface RequestHandler {
    <T> T requestHandler(RetrofitService retrofitService, BaseRequest baseRequest, Class<T> baseResponse, Gson gson) throws IOException, JSONException, IllegalAccessException, InstantiationException;
}
