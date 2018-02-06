package com.zhuying.HttpClient.model;

import java.util.Map;

public class HttpClientRequest {
    //request请求类，拥有属性：url/headers/body
    private static String url;

    private Map<String,String> headers;

    private String body;


    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static String getUrl() {
        return url;
    }
}
