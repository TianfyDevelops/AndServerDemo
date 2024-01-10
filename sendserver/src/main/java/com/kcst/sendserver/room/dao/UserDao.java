package com.kcst.sendserver.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kcst.sendserver.model.UserInfo;

import java.util.List;

@Dao
public interface UserDao {
    
    @Query("SELECT * FROM USERINFO")
    List<UserInfo> getAllUser();


}
