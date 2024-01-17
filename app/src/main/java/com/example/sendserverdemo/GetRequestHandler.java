package com.example.sendserverdemo;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.StringReader;

import retrofit2.Call;
import retrofit2.Response;

public class GetRequestHandler implements RequestHandler {
    @Override
    public <ResponseT> ResponseT requestHandler(RetrofitService retrofitService,
                                                              BaseRequest baseRequest,
                                                              Class<ResponseT> baseResponse) throws IOException {
        Call<String> stringCall = retrofitService.get(baseRequest.headers, baseRequest.path, baseRequest.requestParams);
        Response<String> response = stringCall.execute();
        boolean successful = response.isSuccessful();
        if (successful){
            return new Gson().fromJson(new StringReader(response.body()), baseResponse);
        }
        return null;
    }

}
