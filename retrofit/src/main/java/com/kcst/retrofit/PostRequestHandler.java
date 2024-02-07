package com.kcst.retrofit;

import androidx.constraintlayout.widget.ConstraintSet;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.Response;

public class PostRequestHandler implements RequestHandler {

    @Override
    public <T> Result<T> requestHandler(RetrofitService retrofitService,
                                        BaseRequest baseRequest,
                                        Class<T> responseClazz,
                                        Gson gson) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        if (baseRequest.headers == null || baseRequest.headers.isEmpty()) {
            baseRequest.headers = new HashMap<>();
            baseRequest.headers.put("Content-Type", "application/x-www-form-urlencoded");
        }
        HashMap<String, String> requestParams = new HashMap<>();
        if (baseRequest.getData() != null) {
            Class<?> aClass = baseRequest.getData().getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            Constructor declaredConstructor = aClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            for (Field field : declaredFields) {
                field.setAccessible(true);
                requestParams.put(field.getName(), Objects.requireNonNull(field.get(baseRequest.getData())).toString());
            }
        }
        try {
            Call<String> post = retrofitService.post(baseRequest.headers, baseRequest.path, requestParams);
            Response<String> response = post.execute();
            if (response.isSuccessful()) {
                BaseResponse<T> baseResponse = gson.fromJson(new StringReader(response.body()), BaseResponse.class);
                return Result.success(baseResponse.isSuccess, baseResponse.errorCode, baseResponse.data, baseResponse.errorMsg);
            } else {
                return Result.error(response.code(), response.message());
            }
        } catch (Exception e) {
            return Result.error(501, e.getMessage());
        }

    }
}
