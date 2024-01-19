package com.kcst.retrofit;

public class Result<T> {
    public boolean isSuccess;

    public int code;

    public T data;

    public String message;

    public Result(boolean isSuccess, int code, T data, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> Result<T> success(boolean isSuccess, int code, T data, String message) {
        return new Result<>(isSuccess, code, data, message);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(false,code, null, message);
    }

}
