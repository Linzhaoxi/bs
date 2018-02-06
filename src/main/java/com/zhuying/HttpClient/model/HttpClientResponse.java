package com.zhuying.HttpClient.model;

import java.util.Map;

public class HttpClientResponse {
    //response响应类，拥有属性：statusCode/headers//body
    private String statusCode;

    private Map<String, String> headers;

    private String body;


    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setHeaders(Map<String,String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
