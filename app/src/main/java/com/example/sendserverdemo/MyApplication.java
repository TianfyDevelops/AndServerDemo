package com.example.sendserverdemo;

import android.app.Application;

import com.kcst.retrofit.RetrofitManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.getInstance().initRetrofit("");
    }
}
