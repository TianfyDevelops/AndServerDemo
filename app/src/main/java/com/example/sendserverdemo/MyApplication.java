package com.example.sendserverdemo;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.INSTANCE.initRetrofit("");
    }
}
