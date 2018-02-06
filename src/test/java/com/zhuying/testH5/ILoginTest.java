package com.zhuying.testH5;

import com.zhuying.HttpClient.model.HttpClientResponse;
import com.zhuying.HttpClient.utils.HttpClientUtil;
import com.zhuying.DataProvider.getData;

import com.zhuying.IFModuleParts.postParameters;
import org.apache.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class ILoginTest {
    static final Logger logger = Logger.getLogger(ILoginTest.class.getName());

    @DataProvider(name = "loginPost")
    public Object[][] loginP() throws IOException {
        getData gd = new getData();
        return gd.getXlsData("F:\\JavaIdea\\TestZMF\\src\\main\\resources", "testH5.xlsx", "Login");
//        return gd.getCsvDate("F:\\JavaIdea\\TestZMF\\src\\main\\resources\\testH5Login.csv");
    }


    @Test(dataProvider = "loginPost")
    public void newMethod(String customerMobile,String customerLoginPasswd,String tip){

        //创建登录接口类的对象，调用load方法加载接口。
        postParameters loginpage = new postParameters();
        loginpage.load("https://to.zhongmafu.com:30443/p2p/customerApi/cifCustomer/login");

        //调用login方法，赋值，返回结果为类型HttpClientResponse。
        HttpClientResponse response = loginpage.rawJsonParams("customerMobile",customerMobile,"customerLoginPasswd",customerLoginPasswd);


        //设置断言，响应码是否为00
//        Assert.assertEquals("00",util.formatRespCode(response.getBody().toString()));

        HttpClientUtil util=new HttpClientUtil();
        //判断返回码是否为00，若为00说明登录成功，反之登录失败。将实际结果与预期结果做断言
        if(util.formatRespCode(response.getBody().toString()).equals("00")){
            Assert.assertEquals("成功",tip);
        }
        else{
            Assert.assertEquals("失败",tip);
        }
    }
}
