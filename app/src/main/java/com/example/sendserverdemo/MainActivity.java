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

import com.kcst.sendserver.model.UserInfo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private final String TAG = "MainActivity";
    private RetrofitService service;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindServer();
        service = RetrofitManager.getRetrofit().create(RetrofitService.class);
        findViewById(R.id.btn_get_user_info).setOnClickListener(this);
        findViewById(R.id.btn_set_user_info).setOnClickListener(this);
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
            case R.id.btn_get_user_info:
                requestUserInfo();
                break;
            case R.id.btn_set_user_info:
                requestSetUserInfo();
                break;
        }
    }

    private void requestSetUserInfo() {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId("1");
//        userInfo.setUserName("tianfy");


        HashMap<String, String> map = new HashMap<>();
        map.put("userId", "123");
        map.put("userName", "tianfy");

        Call<String> post = service.post("/user/userInfo", map);

        post.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(TAG, response.toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void requestUserInfo() {
        Call<String> stringCall = service.get(null, "/user/userInfo", null);

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