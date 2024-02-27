package com.kcst.retrofit;

import java.util.Map;

public class BaseRequest<R> {
    public Map<String, String> headers;

    public RequestType requestType;

    public String path;

    public R data;


    public BaseRequest() {

    }


    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "BaseRequest{" +
                "headers=" + headers +
                ", requestType=" + requestType +
                ", path='" + path + '\'' +
                ", data=" + data +
                '}';
    }

    public enum RequestType {

        GET,
        POST
    }

}
