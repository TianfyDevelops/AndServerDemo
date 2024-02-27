package com.kcst.retrofit

import com.google.gson.Gson
import com.kcst.retrofit.R

class PostRequestHandler : RequestHandler {
    override fun <R, T> requestHandler(
        retrofitService: RetrofitService,
        baseRequest: BaseRequest<R>,
        baseResponse: BaseResponse<T>,
        gson: Gson?
    ): T? {
        TODO("Not yet implemented")
    }
    //    public <T extends BaseResponse> T requestHandler(RetrofitService retrofitService,
    //                                        BaseRequest baseRequest,
    //                                        Class<T> responseClazz,
    //                                        Gson gson) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    //
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
    //            Call<String> post = retrofitService.post(baseRequest.headers, baseRequest.path, requestParams);
    //            Response<String> response = post.execute();
    //            if (response.isSuccessful()) {
    //                Type genericSuperclass = responseClazz.getGenericSuperclass();
    //                baseResponse = gson.fromJson(new StringReader(response.body()),genericSuperclass);
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
    //
    //    }
}