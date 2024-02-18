/*
 * Copyright 2018 Zhenjie Yan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kcst.sendserver.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Zhenjie Yan on 2018/6/9.
 */

@Entity
public class UserInfo implements Parcelable {


    @JSONField(name = "id")
    @PrimaryKey()
    private int id = 1;
    @JSONField(name = "mUserId")
    @ColumnInfo
    private String mUserId;
    @JSONField(name = "mUserName")
    @ColumnInfo
    private String mUserName;

    public UserInfo() {
    }

    public UserInfo(int id, String mUserId, String mUserName) {
        this.id = id;
        this.mUserId = mUserId;
        this.mUserName = mUserName;
    }

    protected UserInfo(Parcel in) {
        id = in.readInt();
        mUserId = in.readString();
        mUserName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(mUserId);
        dest.writeString(mUserName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", mUserId='" + mUserId + '\'' +
                ", mUserName='" + mUserName + '\'' +
                '}';
    }
}