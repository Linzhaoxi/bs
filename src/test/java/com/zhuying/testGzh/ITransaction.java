package com.zhuying.testGzh;

import com.zhuying.DataProvider.getData;
import com.zhuying.HttpClient.model.HttpClientResponse;
import com.zhuying.HttpClient.utils.HttpClientUtil;
import com.zhuying.IFModuleParts.postParameters;
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

public class ITransaction {
    static final Logger logger = Logger.getLogger(ITransaction.class.getName());

    @DataProvider(name = "TransactionPost")
    public Object[][] queryP() throws IOException {
        getData gd = new getData();
        return gd.getXlsData("F:\\JavaIdea\\TestZMF\\src\\main\\resources", "testGzh.xlsx", "Sheet2");
    }


    //excel里面：type通道类型，orderNo订单号，transAmt交易金额，payType支付类型，authCode刷卡支付授权码可为空
    @Test(dataProvider = "TransactionPost")
    public void newMethod(String type, String orderNo,  String transAmt,
                          String payType, String authCode,String openid){

        //appKey,subMchitid,subAppid是恒定不变的
        String appKey="d7013e44e019e9a9d550d506a07da79a";
        String subMchtid="24010320";
        String subAppid="wx65d57c6d98ba012c";

        //该通道商户号,店铺名称，是我个人的！！
        String cusId="20171218162135021556";
        String memberName="昭夕Coffee";

        //生成returnUrl，带订单号参数
        String returnUrl="http://pay.zhongmakj.com/Template/retSucc/tranxSN/"+orderNo;

        //subject由商家名称和消费组成
        String subject=memberName+"-消费";
        String notifyUrl="http://pay.zhongmakj.com/Api/notifyUrl";

        //获取付款吗，生成MD5
        String md= MD5.encode(cusId+ Constant.APP_KEY);

        //创建登录接口类的对象，调用load方法加载接口。
        postParameters postparams = new postParameters();

        //生产
        postparams.load("http://118.178.235.184:8080/unionpay/Transaction");
        //测试
//        postparams.load("http://118.178.235.184:8080/unionpay/Refund");

        //调用login方法，赋值，返回结果为类型HttpClientResponse。
        HttpClientResponse response = postparams.wwwFormParams(
                "type",type, "appKey",appKey,
                //通道商户号，不同商家不同
                "cusId",cusId,"orderNo",orderNo,"subject",subject,"transAmt",transAmt,
                //openid不同用户，不同，测试数据为我个人用户（可分为支付宝和微信）
                "openid",openid,"notifyUrl",notifyUrl,"payType",payType,"authCode",authCode,"subMchtid",subMchtid,"subAppid",subAppid,"memberName",memberName,
                "returnUrl",returnUrl,"sign",md);
    }
}
