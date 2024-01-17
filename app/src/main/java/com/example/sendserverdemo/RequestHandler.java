package com.example.sendserverdemo;

import java.io.IOException;

public interface RequestHandler {
    <ResponseT> ResponseT requestHandler(RetrofitService retrofitService, BaseRequest baseRequest, Class<ResponseT> baseResponse) throws IOException;
}
