package com.example.sendserverdemo;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.StringReader;

import retrofit2.Call;
import retrofit2.Response;

public class PostRequestHandler implements RequestHandler {

    @Override
    public <ResponseT> ResponseT requestHandler(RetrofitService retrofitService, BaseRequest baseRequest, Class<ResponseT> baseResponse) throws IOException {
        Call<String> post = retrofitService.post(baseRequest.path, baseRequest.requestParams);
        Response<String> execute = post.execute();
        if (execute.isSuccessful()){
            ResponseT responseT = new Gson().fromJson(new StringReader(execute.body()), baseResponse);
        }
        return null;
    }
}
