package com.example.sendserverdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.kcst.sendserver.AndServerManager;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    private AndServerManager andServerManager;

    @Override
    public void onCreate() {
        super.onCreate();
        andServerManager = new AndServerManager.Builder(this)
                .setPort(8080)
                .setTimeout(5000)
                .setTimeUnit(TimeUnit.MILLISECONDS)
                .build();
        andServerManager.startServer(null);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    private class MyBind extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        andServerManager.stopServer();
        return super.onUnbind(intent);
    }
}
