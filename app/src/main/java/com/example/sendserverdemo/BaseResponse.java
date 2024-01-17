package com.example.sendserverdemo;

public class BaseResponse<T> {
    public boolean isSuccess;

    public int errorCode;

    public String errorMsg;

    public T data;

    @Override
    public String toString() {
        return "BaseData{" +
                "isSuccess=" + isSuccess +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
