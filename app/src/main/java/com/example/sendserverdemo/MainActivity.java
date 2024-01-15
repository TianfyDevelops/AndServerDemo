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
    ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private RetrofitService service;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindServer();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.89:8080/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RetrofitService.class);
        findViewById(R.id.btn_test).setOnClickListener(this);



//        findViewById(R.id.btn_test).setOnClickListener(v -> {
//            Future<Response<BaseData<UserInfo>>> responseFuture = executorService.submit(() -> {
//                Call<BaseData<UserInfo>> userInfo = service.getUserInfo();
//                try {
//                    Response<BaseData<UserInfo>> response = userInfo.execute();
//                    Log.d(MainActivity.class.getSimpleName(), response.toString());
//                    return response;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            });
//            try {
//                Response<BaseData<UserInfo>> response = responseFuture.get();
//
//                if (response.isSuccessful()) {
//                    TextView textView = findViewById(R.id.tv_test);
//                    textView.setText(response.body().toString());
//                }
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        });
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
        switch (v.getId()){
            case R.id.btn_test:{
                requestUserInfo();
            }
        }
    }

    private void requestUserInfo(){
        Call<String> stringCall = service.get("/user/userInfo");

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