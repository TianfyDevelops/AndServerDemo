package com.kcst.retrofit;

import java.util.Map;

public class BaseRequest {
    public Map<String, String> headers;

    public RequestType requestType;

    public String path;

    public Map<String, String> requestParams;


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

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "headers=" + headers +
                ", requestType=" + requestType +
                ", path='" + path + '\'' +
                ", requestParams=" + requestParams +
                '}';
    }

    public enum RequestType {

        GET,
        POST
    }

}
