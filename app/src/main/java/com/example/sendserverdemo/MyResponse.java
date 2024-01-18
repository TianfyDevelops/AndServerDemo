package com.example.sendserverdemo;



public class MyResponse<T> {


    public boolean isSuccess;

    public int errorCode;

    public String errorMsg;

    private T data;



}
