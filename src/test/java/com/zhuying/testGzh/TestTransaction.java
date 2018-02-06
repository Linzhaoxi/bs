package com.zhuying.testGzh;

import com.zhuying.DataProvider.getData;
import com.zhuying.testH5.TestLogin;
import com.zmkj.utils.encrypt.MD5;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestTransaction {
    static final Logger logger = Logger.getLogger(TestLogin.class.getName());

    @DataProvider(name = "TransactionPost")
    public Object[][] queryP() throws IOException {
        getData gd = new getData();
        return gd.getXlsData("F:\\JavaIdea\\TestZMF\\src\\main\\resources", "testGzh.xlsx", "Sheet2");
    }


    @Test(dataProvider = "TransactionPost")
    public void newMethod(String type, String cusId, String orderNo,  String transAmt,
                          String openid,String payType, String authCode, String memberName){

        logger.info("------------------------开始交易 Transaction--------------------------");

        //appKey,subMchitid,subAppid是恒定不变的
        String appKey="d7013e44e019e9a9d550d506a07da79a";
        String subMchtid="24010320";
        String subAppid="wx65d57c6d98ba012c";

        //生成returnUrl，带订单号参数
        String returnUrl="http://pay.zhongmakj.com/Template/retSucc/tranxSN/"+orderNo;

        //subject由商家名称和消费组成
        String subject=memberName+"-消费";
        String notifyUrl="http://pay.zhongmakj.com/Api/notifyUrl";

        //获取付款吗，生成MD5
        String md= MD5.encode(cusId+ Constant.APP_KEY);

        logger.info("所有数据获取成功！");
        doPost p1=new doPost();

        //设置Body参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", type));
        params.add(new BasicNameValuePair("appKey", appKey));
        //通道商户号，不同商家不同
        params.add(new BasicNameValuePair("cusId", cusId));
        params.add(new BasicNameValuePair("orderNo", orderNo));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("transAmt", transAmt));
        //openid不同用户，不同，测试数据为我个人用户（可分为支付宝和微信）
        params.add(new BasicNameValuePair("openid", openid));
        params.add(new BasicNameValuePair("notifyUrl", notifyUrl));
        params.add(new BasicNameValuePair("payType", payType));
        params.add(new BasicNameValuePair("authCode", authCode));
        params.add(new BasicNameValuePair("subMchtid", subMchtid));
        params.add(new BasicNameValuePair("subAppid", subAppid));
        params.add(new BasicNameValuePair("memberName", memberName));


        params.add(new BasicNameValuePair("returnUrl", returnUrl));
        params.add(new BasicNameValuePair("sign", md));

        logger.info("数据请求body添加成功！");

        //获取响应body，提交的为transaction接口
        String finResult=p1.doPost(Constant.PRO_ADDR+"Transaction",params);
        logger.info("返回结果："+finResult);
        logger.info("------------------------结束交易 Transaction--------------------------");
        System.out.println(finResult);

    }
}
