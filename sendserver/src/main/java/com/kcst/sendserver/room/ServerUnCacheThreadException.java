package com.kcst.sendserver.room;

import android.util.Log;

import androidx.annotation.NonNull;

public class ServerUnCacheThreadException implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "ServerUnCacheThreadException";

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.d(TAG, "ThreadName=" + t.getName() + "Exception=" + e.getMessage());
    }
}
