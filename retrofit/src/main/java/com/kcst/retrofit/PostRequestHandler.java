package com.kcst.retrofit;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

public class PostRequestHandler implements RequestHandler {

    @Override
    public <T> Result<T> requestHandler(RetrofitService retrofitService,
                                        BaseRequest baseRequest,
                                        Class<T> responseClazz,
                                        Gson gson) {

        if (baseRequest.headers == null || baseRequest.headers.isEmpty()) {
            baseRequest.headers = new HashMap<>();
            baseRequest.headers.put("Content-Type", "application/x-www-form-urlencoded");
        }
        if (baseRequest.requestParams == null || baseRequest.requestParams.isEmpty()) {
            baseRequest.requestParams = new HashMap<>();
        }
        try {
            Call<String> post = retrofitService.post(baseRequest.headers, baseRequest.path, baseRequest.requestParams);
            Response<String> response = post.execute();
            if (response.isSuccessful()) {
                Type type = responseClazz.getGenericSuperclass();
                BaseResponse<T> baseResponse = gson.fromJson(new StringReader(response.body()), type);
                return Result.success(baseResponse.isSuccess,baseResponse.errorCode, baseResponse.data, baseResponse.errorMsg);
            } else {
                return Result.error(response.code(), response.message());
            }
        } catch (Exception e) {
            return Result.error(501, e.getMessage());
        }

    }
}
