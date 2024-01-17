package com.example.sendserverdemo;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    public static volatile RetrofitManager INSTANCE;

    private final String DEFAULT_URL = "http://192.168.1.89:8080/";

    public RetrofitManager getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitManager();
                }
            }
        }
        return INSTANCE;
    }

    private RetrofitManager() {
    }

    private Retrofit retrofit;

    /**
     * 初始化Retrofit
     * @param baseUrl endIndex must "/"
     */
    public void initRetrofit(String baseUrl) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
        String url = TextUtils.isEmpty(baseUrl) ? DEFAULT_URL : baseUrl;
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        if (retrofit != null) {
            return retrofit;
        } else {
            throw new NullPointerException("retrofit is null, please invoke initRetrofit() method before call getRetrofit()");
        }
    }

}
