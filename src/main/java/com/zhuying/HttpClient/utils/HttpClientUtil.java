package com.zhuying.HttpClient.utils;

import com.zhuying.HttpClient.model.HttpClientRequest;
import com.zhuying.HttpClient.model.HttpClientResponse;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HttpClientUtil {

    //http客户端工具类，拥有get/post/delete/put常用的请求方式

    private static Logger logger=Logger.getLogger(String.valueOf(HttpClientUtil.class));
    private CloseableHttpClient httpClient;
    /*
    实例化HttpClientUtil对象，调用init()方法初始化，初始化为了实例化HttpClient对象。
    执行请求，获得请求对象的url属性，实例化HttpGet/HttpPost/HttpDelete/HttpPut对象
    调用sendRequest()方法，发送请求。
     */

    //get
    public static HttpClientResponse doGet(HttpClientRequest httpClientRequest){
        com.zhuying.HttpClient.utils.HttpClientUtil httpClientUtil=new com.zhuying.HttpClient.utils.HttpClientUtil();
        httpClientUtil.init();
        HttpGet httpGet=new HttpGet(HttpClientRequest.getUrl());
        return httpClientUtil.sendRequest(httpGet,httpClientRequest);
    }

    //post
    public static HttpClientResponse doPost(HttpClientRequest httpClientRequest){
        com.zhuying.HttpClient.utils.HttpClientUtil httpClientUtil=new com.zhuying.HttpClient.utils.HttpClientUtil();
        httpClientUtil.init();
        HttpPost httpPost=new HttpPost(HttpClientRequest.getUrl());
        return httpClientUtil.sendRequest(httpPost,httpClientRequest);
    }

    //delete
    public static HttpClientResponse doDelete(HttpClientRequest httpClientRequest){
        com.zhuying.HttpClient.utils.HttpClientUtil httpClientUtil=new com.zhuying.HttpClient.utils.HttpClientUtil();
        httpClientUtil.init();
        HttpDelete httpDelete=new HttpDelete(HttpClientRequest.getUrl());
        return httpClientUtil.sendRequest(httpDelete,httpClientRequest);
    }

    //put
    public static HttpClientResponse doPut(HttpClientRequest httpClientRequest){
        com.zhuying.HttpClient.utils.HttpClientUtil httpClientUtil=new com.zhuying.HttpClient.utils.HttpClientUtil();
        httpClientUtil.init();
        HttpPut httpPut=new HttpPut(HttpClientRequest.getUrl());
        return httpClientUtil.sendRequest(httpPut,httpClientRequest);
    }

    //1.初始化链接，创建httpclient对象
    private void init(){
        //HttpClientBuilder创建CloseableHttpClient实例
        httpClient= HttpClientBuilder.create().build();
        logger.info("start init http connection");
    }

    //2.发送请求
    private HttpClientResponse sendRequest(HttpRequestBase httpRequestBase, HttpClientRequest httpClientRequest){

        //创建返回的对象
        HttpClientResponse httpClientresponse=new HttpClientResponse();
        //默认body编码
        String encodingOfBody="UTF-8";
        //获取请求headers
        Map<String,String> requestHeaders=httpClientRequest.getHeaders();
        for(String key:requestHeaders.keySet()){
            //设置请求headers键值
            httpRequestBase.setHeader(key,requestHeaders.get(key));
            //key为content-type时
            if(key.toLowerCase().equals("content-type")){
                //获取content-type的值
                String contentType=requestHeaders.get(key);
                //若格式如application/json;charset=UTF-8 获取第二个的编码
                if(contentType.split(";").length>=2){
                    encodingOfBody=contentType.split(";")[1].split("=")[1];
                }
            }
        }

        //获取请求body String
        //HttpEntityEnclosingRequestBase ->HttpPatch, HttpPost, HttpPut只有这三个有body
        if(httpRequestBase instanceof HttpEntityEnclosingRequestBase){
            //设置请求body键值，规定请求体编码方式（可选）。
            ((HttpEntityEnclosingRequestBase) httpRequestBase).setEntity(new StringEntity(httpClientRequest.getBody(),encodingOfBody));
            logger.info("request body is:"+httpClientRequest.getBody());
        }


        try {
            //执行请求获得响应对象
            CloseableHttpResponse response=httpClient.execute(httpRequestBase);
            //响应码
            String statusCode=response.getStatusLine().toString().split(" ")[1];
            logger.info("statusCode is:"+statusCode);
            httpClientresponse.setStatusCode(statusCode);

            //httpHeaders Map<String,String>
            Header[] headers=response.getAllHeaders();
            Map<String,String> responseHeaders=new HashMap<String, String>();
            for (Header header:headers){
                logger.info(header.getName()+": "+header.getValue());
                requestHeaders.put(header.getName(),header.getValue());
            }
            httpClientresponse.setHeaders(responseHeaders);

            //body String
            HttpEntity entity=response.getEntity();
//            String body= IOUtils.toString(entity.getContent());
            String body = EntityUtils.toString(entity);
//            logger.info("body is:"+body);
            httpClientresponse.setBody(body);

            try {
                JSONObject jsonobj=new JSONObject(body);
                logger.info("json body is:"+jsonobj.toString());
            } catch (JSONException e) {
                logger.info("body is:"+body);
                logger.error("JSON error.");
                logger.error(e.getMessage());
            }

//            //获得响应码，设置断言
//            this.formatRespCode(body);

            //关闭链接
            this.close();
        } catch (IOException e) {
            logger.error("IOException error.");
            logger.error(e.getMessage());
        }
        return httpClientresponse;

    }

    //3.关闭链接
    private void close(){
        try {
            httpClient.close();
            logger.info("close http connection successfully!");
        } catch (IOException e) {
            logger.error("Close http connection failed.");
            logger.error(e.getMessage());
        }
    }


    //以下方法一般为了获取响应中的某个值用于断言
    //获得响应码
    public String formatRespCode(String responseBody){
        String result="";
        try {
            JSONObject jsonObject=new JSONObject(responseBody);
            result=jsonObject.get("respCode").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    //获得描述信息
    public String formatRespDesc(String responseBody){
        String result="";
        try {
            JSONObject jsonObject=new JSONObject(responseBody);
            result=jsonObject.get("respDesc").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    //格式化url，只有get请求的时候一般
    public String formatUrl(String url,Map<String, String>queryParams){
        String result="";
        String params="";
        for(String key:queryParams.keySet()){
            params += (key + "=" +queryParams.get(key) + "&");
        }
        result += (url + "?" + params.substring(0, params.length()-1));
        return result;
    }
}
