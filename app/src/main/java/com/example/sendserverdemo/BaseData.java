package com.example.sendserverdemo;

public class BaseData<T> {
    private boolean isSuccess;

    private int errorCode;

    private String errorMsg;

    private T data;

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
