package com.zhuying.testH5;

public class Constant {

    //url地址
    public static final String MAIN_ADDR="https://to.zhongmafu.com:30443/h5/";

    //登录元素名称 - 用户名/密码
    public static final String LOGIN_YHM="mobilePhone";
    public static final String LOGIN_MM="userPassword";
    public static final String LOGIN_BUTTON="//button";

    //截屏地址
    public static final String SCREEN_SHOT="F:\\screenshots\\";

    //理财、我的
    public static final String CLICK_LICAI="//div[2]/div[1]/p";
    public static final String CLICK_MY="//div[2]/div[2]/p";

    //理财页面-优选理财、我的理财
    public static final String LICAI_YOUXUAN="//p";
    public static final String LICAI_MY="//div[2]/p";

    //我的页面-我要提现、身份认证、关于我们、设置
    public static final String CLICK_WYTX="//span";
    public static final String CLICK_SFRZ="//li[1]";
    public static final String CLICK_ABOUTUS="//li[2]";
    public static final String CLICK_SETTING="//li[3]";

    //设置-支付密码、登录密码、个人资料、银行卡、清除缓存
    public static final String CLICK_SETTING_PAYMM="//p";
    public static final String CLICK_SETTING_LOGINMM="//div[2]/i";
    public static final String CLICK_SETTING_MYINFO="//div[3]/div/p";
    public static final String CLICK_SETTING_MYCARD="//div[3]/div[2]/p";
    public static final String CLICK_SETTING_CLEAR="//div[4]/p";

    //支付密码-旧密码、新密码、确认新密码
    public static final String CLICK_SETTING_PAYMM_OLD="accountPayPasswd";
    public static final String CLICK_SETTING_PAYMM_NEW1="newAccountPayPasswd";
    public static final String CLICK_SETTING_PAYMM_NEW2="commitnewAccountPayPasswd";
    public static final String CLICK_SETTING_PAYMM_OK="//button[@type='submit'])[2]";


    //登录密码-旧密码、新密码、确认新密码
    public static final String CLICK_SETTING_LOGINMM_OLD="customerLoginPasswd";
    public static final String CLICK_SETTING_LOGINMM_NEW1="newCustomerLoginPasswd";
    public static final String CLICK_SETTING_LOGINMM_NEW2="commitnewCustomerLoginPasswd";
    public static final String CLICK_SETTING_LOGINMM_OK="//button[@type='submit']";



}
