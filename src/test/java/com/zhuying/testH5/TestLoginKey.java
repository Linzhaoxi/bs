package com.zhuying.testH5;

import com.zhuying.DataProvider.getData;
import com.zhuying.DriverProvider.getDriver;
import com.zhuying.WebModuleParts.changeKeyUI;
import com.zhuying.WebModuleParts.loginUI;
import com.zhuying.screenShot;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestLoginKey {
    static final Logger logger = Logger.getLogger(TestLoginKey.class.getName());
    private WebDriver driver;
    private WebDriverWait wait;
    private int i=0;

    //测试数据
    @DataProvider(name="keyTable")
    public Object[][] Users() throws IOException {
        getData gd = new getData();
        return gd.getXlsData("F:\\JavaIdea\\TestZMF\\src\\main\\resources", "testH5.xlsx", "LoginKey");
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() {

        logger.info("# # # # # # # # # # # #修改登录密码 # # # # # # # # # # # # # # # ");
        getDriver gd = new getDriver();
//        driver = gd.linkChrome();
        driver = gd.linkFirefox();

        //输入正确的用户名密码成功登录
        loginUI loginui=new loginUI(driver);
        loginui.load("https://to.zhongmafu.com:30443/h5/");
        loginui.putData("mobilePhone","13588057343","userPassword","zhuying","//button");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constant.CLICK_MY))).click();
//        driver.findElement(By.xpath("//div[2]/div[2]/p")).click();
        logger.info("点击-我的");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constant.CLICK_SETTING))).click();
//        driver.findElement(By.xpath("//li[3]")).click();
        logger.info("点击-设置");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constant.CLICK_SETTING_LOGINMM))).click();
//        driver.findElement(By.xpath("//div[2]/i")).click();
        logger.info("点击-登录密码");

    }

    @Test(dataProvider="keyTable")
    public void testLoginKey(String old,String new1,String new2,String tip) throws Exception {
        //进入修改页面，输入值
        changeKeyUI changeKeyui = new changeKeyUI(driver);
        changeKeyui.putData("customerLoginPasswd",old,"newCustomerLoginPasswd", new1,
                "commitnewCustomerLoginPasswd",new2,"//button[@type='submit']");

        //截屏
        screenShot screenshot = new screenShot(driver);
        screenshot.makeShot("F:\\screenshots\\","LoginKey_test",i);
        i++;

    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        //	Quits the driver and closes all the associated window of that driver.
        driver.close();
        logger.info("已退出.");
        logger.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
    }
}
