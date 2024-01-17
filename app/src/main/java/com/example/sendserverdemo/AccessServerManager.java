package com.example.sendserverdemo;


public class AccessServerManager {
    private static volatile AccessServerManager INSTANCE;

    public static AccessServerManager getInstance() {
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
        retrofitService = RetrofitManager.getRetrofit().create(RetrofitService.class);
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
