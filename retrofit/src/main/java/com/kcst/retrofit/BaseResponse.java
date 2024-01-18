package com.kcst.retrofit;

public class BaseResponse{
    public boolean isSuccess;

    public int errorCode;

    public String errorMsg;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "isSuccess=" + isSuccess +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
