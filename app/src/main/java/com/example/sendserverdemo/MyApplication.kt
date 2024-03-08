package com.example.sendserverdemo

import android.app.Application
import com.kcst.retrofit.net.RetrofitManager

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitManager.Companion.INSTANCE.initRetrofit("http://192.168.1.89:8080")
    }
}