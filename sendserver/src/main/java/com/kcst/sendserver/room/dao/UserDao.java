package com.kcst.sendserver.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kcst.sendserver.model.UserInfo;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM USERINFO")
    List<UserInfo> getAllUser();

    @Query("SELECT * FROM USERINFO WHERE mUserId = :userId")
    UserInfo getUserForId(String userId);

    @Insert(entity = UserInfo.class)
    void insertUser(UserInfo userInfo);


}
