package com.zhuying.testGzh;

import com.zhuying.DataProvider.getData;
import com.zmkj.utils.encrypt.MD5;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestQuery {
    static final Logger logger = Logger.getLogger(TestQuery.class.getName());

    @DataProvider(name = "queryPost")
    public Object[][] queryP() throws IOException {
        getData gd = new getData();
        return gd.getXlsData("F:\\JavaIdea\\TestZMF\\src\\main\\resources", "testGzh.xlsx", "Query");
    }


    @Test(dataProvider = "queryPost")
    public void newMethod(String trxid){
        logger.info("------------------------开始交易 Query--------------------------");
        //获取付款吗，生成MD5
        String md= MD5.encode(trxid+ Constant.APP_KEY);

        logger.info("所有数据获取成功！");
        doPost p1=new doPost();

        //设置Body参数，退款码
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("trxid", trxid));
        params.add(new BasicNameValuePair("sign", md));

        logger.info("数据请求body添加成功！");

        //获取响应body，查询接口
        String finResult=p1.doPost(Constant.PRO_ADDR+"Query",params);
        logger.info("返回结果："+finResult);
        logger.info("------------------------结束交易 Query--------------------------");
        System.out.println(finResult);

    }
}
