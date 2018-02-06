package com.zhuying.IFModuleParts;

import com.zhuying.HttpClient.model.HttpClientRequest;
import com.zhuying.HttpClient.model.HttpClientResponse;
import com.zhuying.HttpClient.utils.HttpClientUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class postParameters {
    static final Logger logger = Logger.getLogger(postParameters.class.getName());
    HttpClientRequest request=new HttpClientRequest();

    //设置接口地址，加载
    public void load(String url){
        logger.info("------------------------开始加载，请求接口--------------------------");
        request.setUrl(url);
    }

//    设置提交的header和body，post方式提交登录信息
    //参数：请求键1，值1，请求键2，值2

//    public HttpClientResponse putParams(String text1, String val1, String text2, String val2){
//
//        //设置headers参数
//        Map<String,String> requestHeaders=new HashMap<String, String>();
//        requestHeaders.put("Content-Type","application/json;charset=UTF-8");
//        request.setHeaders(requestHeaders);
//        logger.info("request请求headers："+request.getHeaders());
//
//        //设置Body参数 key-value
//        Map<String,String> requestBody=new HashMap<String, String>();
//        requestBody.put(text1,val1);
//        requestBody.put(text2, val2);
//        //请求方式是post，body为json格式（可选）
//        request.setBody((new JSONObject(requestBody)).toString());
//        logger.info("request请求body："+request.getBody());
//
//        //获取响应body
//        HttpClientResponse response= HttpClientUtil.doPost(request);
//        logger.info("response响应body："+response.getBody());
//        logger.info("------------------------请求结束-------------------------");
//        return response;
//    }

    public HttpClientResponse rawJsonParams(String... args){
        //设置headers参数
        Map<String,String> requestHeaders=new HashMap<String, String>();
        requestHeaders.put("Content-Type","application/json;charset=UTF-8");
//        requestHeaders.put("Content-Type","application/x-www-form-urlencoded");
        request.setHeaders(requestHeaders);
        logger.info("request请求headers："+request.getHeaders());

        //设置Body参数 key-value
        Map<String,String> requestBody=new HashMap<String, String>();
        for(int i=0; i<args.length;){
            requestBody.put(args[i],args[i+1]);
            i+=2;
        }
        //请求方式是post，body为json格式（可选）
        request.setBody((new JSONObject(requestBody)).toString());
        logger.info("request请求body："+request.getBody());

        //获取响应body
        HttpClientResponse response= HttpClientUtil.doPost(request);
        logger.info("response响应body："+response.getBody());
        logger.info("---------------------------请求结束-----------------------------");
        return response;
    }

    public HttpClientResponse wwwFormParams(String... args){
        //设置headers参数
        Map<String,String> requestHeaders=new HashMap<String, String>();
        requestHeaders.put("Content-Type","application/x-www-form-urlencoded");
        request.setHeaders(requestHeaders);
        logger.info("request请求headers："+request.getHeaders());

//        设置Body参数 key1=val&key2=val2
        StringBuffer params=new StringBuffer();
        for(int i=0; i<args.length;){
            params.append(args[i]+"="+args[i+1]+"&");
            i+=2;
        }
        params.deleteCharAt(params.length()-1);


        //请求方式是post，body为json格式（可选）
        request.setBody(params.toString());
        logger.info("request请求body："+request.getBody());

        //获取响应body
        HttpClientResponse response= HttpClientUtil.doPost(request);
        logger.info("response响应body："+response.getBody());
        logger.info("---------------------------请求结束-----------------------------");
        return response;
    }

}
