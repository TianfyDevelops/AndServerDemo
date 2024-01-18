package com.kcst.retrofit;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

public class PostRequestHandler implements RequestHandler {

    @Override
    public <T> T requestHandler(RetrofitService retrofitService,
                                                BaseRequest baseRequest,
                                                Class<T> baseResponse,
                                                Gson gson) throws IOException {

        if (baseRequest.headers == null || baseRequest.headers.isEmpty()) {
            baseRequest.headers = new HashMap<>();
            baseRequest.headers.put("Content-Type", "application/x-www-form-urlencoded");
        }
        if (baseRequest.requestParams == null || baseRequest.requestParams.isEmpty()) {
            baseRequest.requestParams = new HashMap<>();
        }

        Call<String> post = retrofitService.post(baseRequest.headers, baseRequest.path, baseRequest.requestParams);
        Response<String> response = post.execute();
        if (response.isSuccessful()) {
            return  gson.fromJson(new StringReader(response.body()), baseResponse);
        } else {
            throw new IOException(response.code() + response.message());
        }
    }
}
