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
import android.widget.TextView;

import com.kcst.sendserver.model.UserInfo;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private final String TAG = "MainActivity";

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

        RetrofitService service = retrofit.create(RetrofitService.class);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        findViewById(R.id.btn_test).setOnClickListener(v -> {
            scheduledExecutorService.execute(() -> {
                Call<UserInfo> userInfo = service.getUserInfo();
                try {
                    Response<UserInfo> response = userInfo.execute();
                    Log.d(MainActivity.class.getSimpleName(), response.toString());
                    if (response.isSuccessful()) {
                        TextView textView = findViewById(R.id.tv_test);
                        textView.setText(response.toString());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

        });
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
}