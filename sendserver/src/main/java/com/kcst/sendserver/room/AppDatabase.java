package com.kcst.sendserver.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kcst.sendserver.model.UserInfo;
import com.kcst.sendserver.room.dao.UserDao;

@Database(entities = {UserInfo.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
