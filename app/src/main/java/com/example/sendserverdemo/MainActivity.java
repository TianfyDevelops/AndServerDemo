package com.example.sendserverdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kcst.sendserver.model.UserInfo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private final String TAG = "MainActivity";
    private RetrofitService service;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindServer();
        service = RetrofitManager.INSTANCE.getRetrofit().create(RetrofitService.class);
        findViewById(R.id.btn_test).setOnClickListener(this);
    }

    private void bindServer() {
        bindService(new Intent(this, MyService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "--------------service connected-------------");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "--------------service disconnected-------------");

            }
        }, Context.BIND_AUTO_CREATE);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test: {
                requestUserInfo();
            }
        }
    }

    private void requestUserInfo() {
        Call<String> stringCall = service.get(null,"/user/userInfo",null);

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(TAG, response.toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}