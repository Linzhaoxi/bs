package com.zhuying.testGzh;

import com.zmkj.utils.encrypt.MD5;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestRefund {
    static final Logger logger = Logger.getLogger(TestRefund.class.getName());

    @DataProvider(name = "refundPost")
    public Object[][] queryP() throws IOException {
//        getData gd = new getData();
//        return gd.getXlsData("F:\\JavaIdea\\TestZMF\\src\\main\\resources", "testGzh.xlsx", "Refund");
        return new Object[][]{
                {"2017122215543601811182205620","1","YPR12221554362682811"}
        };

    }


    @Test(dataProvider = "refundPost")
    public void newMethod(String trxid,String refundAmt,String tranxSN){
        logger.info("------------------------开始交易 Refund--------------------------");
        //获取付款吗，生成MD5
        String md= MD5.encode(trxid+ Constant.APP_KEY);

        logger.info("所有数据获取成功！");
        doPost p1=new doPost();

        //设置Body参数，退款码
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("trxid", trxid));
        params.add(new BasicNameValuePair("refundAmt", refundAmt));
        params.add(new BasicNameValuePair("tranxSN", tranxSN));
        params.add(new BasicNameValuePair("sign", md));

        logger.info("数据请求body添加成功！");

        //获取响应body，查询接口
        String finResult=p1.doPost(Constant.PRO_ADDR+"Refund",params);
        logger.info("返回结果："+finResult);
        logger.info("------------------------结束交易 Refund--------------------------");
        System.out.println(finResult);

    }
}
