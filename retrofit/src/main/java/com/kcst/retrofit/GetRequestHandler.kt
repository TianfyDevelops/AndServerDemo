package com.kcst.retrofit

import com.google.gson.Gson

class GetRequestHandler : RequestHandler {


    //    @Override
    //    public <R,T extends BaseResponse> T requestHandler(RetrofitService retrofitService,
    //                                        BaseRequest<R> baseRequest,
    //                                        Class<T> responseClazz, Gson gson) {
    //        if (baseRequest.headers == null || baseRequest.headers.isEmpty()) {
    //            baseRequest.headers = new HashMap<>();
    //            baseRequest.headers.put("Content-Type", "application/x-www-form-urlencoded");
    //        }
    //        HashMap<String, String> requestParams = new HashMap<>();
    //        if (baseRequest.data != null) {
    //            Class<?> aClass = baseRequest.data.getClass();
    //            Field[] declaredFields = aClass.getDeclaredFields();
    //            Constructor declaredConstructor = aClass.getDeclaredConstructor();
    //            declaredConstructor.setAccessible(true);
    //            for (Field field : declaredFields) {
    //                field.setAccessible(true);
    //                requestParams.put(field.getName(), Objects.requireNonNull(field.get(baseRequest.data)).toString());
    //            }
    //        }
    //        T baseResponse;
    //        try {
    //            Call<String> stringCall = retrofitService.get(baseRequest.headers, baseRequest.path, requestParams);
    //            Response<String> response = stringCall.execute();
    //            if (response.isSuccessful()) {
    //                baseResponse = gson.fromJson(new StringReader(response.body()),responseClazz);
    //            } else {
    //                baseResponse = responseClazz.newInstance();
    //                baseResponse.isSuccess=false;
    //                baseResponse.errorCode=response.code();
    //                baseResponse.errorMsg=response.message();
    //            }
    //            return baseResponse;
    //        } catch (Exception e) {
    //            baseResponse = responseClazz.newInstance();
    //            baseResponse.isSuccess=false;
    //            baseResponse.errorCode=501;
    //            baseResponse.errorMsg=e.getMessage();
    //            return baseResponse;
    //        }
    //    }
    override fun <R, T> requestHandler(
        retrofitService: RetrofitService,
        baseRequest: BaseRequest<R>,
        baseResponse: BaseResponse<T>,
        gson: Gson?
    ): T? {
        if (baseRequest.headers == null){
            baseRequest.headers= mutableMapOf<String,String>().apply {
                put("Content-Type","application/x-www-form-urlencoded")
            }
        }
        val requestParams = mutableMapOf<String,String>()
        if (baseRequest.getRequestData()!=null){
            val baseRequestClazz = baseRequest.getRequestData()!!::class.java
            baseRequestClazz.declaredFields.forEach {
                requestParams[it.name]= it.get(baseRequest)!!.toString()
            }
        }


        val call = retrofitService.get(baseRequest.headers, baseRequest.getPath(), requestParams)
        val strResponse = call.execute()
        return null
    }
}