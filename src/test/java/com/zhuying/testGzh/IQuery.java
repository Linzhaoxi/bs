package com.zhuying.testGzh;

import com.zhuying.DataProvider.getData;
import com.zhuying.HttpClient.model.HttpClientResponse;
import com.zhuying.HttpClient.utils.HttpClientUtil;
import com.zhuying.IFModuleParts.postParameters;
import com.zmkj.utils.encrypt.MD5;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class IQuery {
    static final Logger logger = Logger.getLogger(IQuery.class.getName());

    @DataProvider(name = "queryPost")
    public Object[][] queryP() throws IOException {
        getData gd = new getData();
        return gd.getXlsData("F:\\JavaIdea\\TestZMF\\src\\main\\resources", "testGzh.xlsx", "Query");
    }

    @Test(dataProvider = "queryPost")
    public void newMethod(String trxid,String tip){

        //获取付款吗，生成MD5
        String md= MD5.encode(trxid+ Constant.APP_KEY);

        //创建登录接口类的对象，调用load方法加载接口。
        postParameters postparams = new postParameters();

        //生产
        postparams.load("http://118.178.235.184:8080/unionpay/Query");
        //测试
//        postparams.load("http://118.178.235.184:8080/unionpay/Query");

        //调用login方法，赋值，返回结果为类型HttpClientResponse。
        HttpClientResponse response = postparams.wwwFormParams("trxid",trxid,"sign",md);

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
