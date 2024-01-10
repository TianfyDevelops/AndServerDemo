package com.kcst.sendserver.room;

import android.content.Context;

import androidx.room.Room;

/**
 * @author Tianfy
 * Room数据库管理类
 */
public class RoomManager {
    private static final RoomManager INSTANCE = new RoomManager();

    public static RoomManager getInstance() {
        return INSTANCE;
    }

    private RoomManager() {
    }

    private AppDatabase appDatabase;

    public void init(Context context) {
        appDatabase = Room.databaseBuilder(
                context,
                AppDatabase.class,
                "kcst-sync-data"
        ).build();
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

}
