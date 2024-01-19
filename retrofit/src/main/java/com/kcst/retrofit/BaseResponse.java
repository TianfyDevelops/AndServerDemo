package com.kcst.retrofit;

public class BaseResponse<T> {
    public boolean isSuccess;

    public int errorCode;

    public String errorMsg;

    public T data;


    @Override
    public String toString() {
        return "BaseResponse{" +
                "isSuccess=" + isSuccess +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
