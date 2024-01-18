package com.kcst.retrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

public class GetRequestHandler implements RequestHandler {
    @Override
    public <T> T requestHandler(RetrofitService retrofitService,
                                                BaseRequest baseRequest,
                                                Class<T> baseResponse, Gson gson) throws IOException, JSONException, IllegalAccessException, InstantiationException {
        if (baseRequest.headers == null || baseRequest.headers.isEmpty()) {
            baseRequest.headers = new HashMap<>();
            baseRequest.headers.put("Content-Type", "application/x-www-form-urlencoded");
        }
        if (baseRequest.requestParams == null || baseRequest.requestParams.isEmpty()) {
            baseRequest.requestParams = new HashMap<>();
        }
        Call<String> stringCall = retrofitService.get(baseRequest.headers, baseRequest.path, baseRequest.requestParams);
        Response<String> response = stringCall.execute();
        if (response.isSuccessful()) {
            return gson.fromJson(new StringReader(response.body()), baseResponse);
        } else {
            throw new IOException(response.code() + response.message());
        }
    }

}
