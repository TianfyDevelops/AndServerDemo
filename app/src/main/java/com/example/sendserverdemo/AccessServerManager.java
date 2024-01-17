package com.example.sendserverdemo;

import retrofit2.Call;

public class AccessServerManager {
    public static volatile AccessServerManager INSTANCE;

    public AccessServerManager getInstance() {
        if (INSTANCE == null) {
            synchronized (AccessServerManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AccessServerManager();
                }
            }
        }
        return INSTANCE;
    }

    private RetrofitService retrofitService;

    private AccessServerManager() {
        retrofitService = RetrofitManager.INSTANCE.getRetrofit().create(RetrofitService.class);
    }


    public <T> T request(BaseRequest baseRequest, BaseResponse<T> baseResponse) {

        RequestHandler requestHandler = null;
        switch (baseRequest.requestType){
            case GET:
                requestHandler = new GetRequestHandler();
                break;
            case POST:
                requestHandler = new PostRequestHandler();
                break;
        }

        requestHandler.requestHandler(retrofitService,baseRequest,baseResponse);



        return null;
    }


}
