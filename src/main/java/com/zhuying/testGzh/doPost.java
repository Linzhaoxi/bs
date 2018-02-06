package com.zhuying.testGzh;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class doPost {
    private static Logger logger=Logger.getLogger(doPost.class.getName());
    private CloseableHttpClient client;
    private String body="hahaha----error!";

    public String doPost(String url,List<NameValuePair> params){

        //初始化
        client= HttpClientBuilder.create().build();
        logger.info("start init http connection");

        //获得URL
        HttpPost httppost = new HttpPost(url);

        try {
            // Post请求
            // 设置参数
            httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            logger.info("request body is:"+params.toString());

            // 发送请求
            HttpResponse httpresponse = client.execute(httppost);
            //响应码
            String statusCode=httpresponse.getStatusLine().toString().split(" ")[1];
            logger.info("statusCode is:"+statusCode);

            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            client.close();
            logger.info("close http connection successfully!");
        } catch (IOException e) {
            logger.error("Close http connection failed.");
            logger.error(e.getMessage());
        }

        return body;

    }
}
