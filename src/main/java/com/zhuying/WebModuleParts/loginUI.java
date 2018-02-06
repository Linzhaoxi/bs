package com.zhuying.WebModuleParts;

import com.zhuying.testH5.Constant;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginUI {
    static final Logger logger = Logger.getLogger(loginUI.class.getName());
    private WebDriver driver=null;

    public loginUI(WebDriver driver) {
        this.driver = driver;
    }

    public void load(String url){
        //获取url地址
        driver.get(url);
        logger.info("已获取地址栏信息"+url);
    }

    //输入数据参数：用户名文本框、用户名、密码文本框、密码、登录按钮
    public void putData(String userTxt,String userName,String passTxt,String passWord,String Loginbtn){
        //输入数据
        try {
            //从输入框清空内容
            driver.findElement(By.name(userTxt)).clear();
            //输入一些文本输入框
            driver.findElement(By.name(userTxt)).sendKeys(userName);
            logger.info("用户名已输入.");
            driver.findElement(By.name(passTxt)).clear();
            driver.findElement(By.name(passTxt)).sendKeys(passWord);
            logger.info("密码已输入.");

            //输入数据以后强制等待1s钟，再点击登录按钮
            Thread.sleep(1000);
            //判断loginbtn是xpath还是name。
            if(Loginbtn.indexOf("//")<0){
                //找不到字符串“//”则为name
                driver.findElement(By.name(Loginbtn)).click();
            }
            else {
                //否则为xpath
                driver.findElement(By.xpath(Loginbtn)).click();
            }
            logger.info("登陆按钮已点击.");
//            Thread.sleep(2000);
        }catch(Exception e1){
            logger.error("未发现文本框.");
            logger.error(e1);
        }
    }


}
