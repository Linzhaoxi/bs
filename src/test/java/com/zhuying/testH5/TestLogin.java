package com.zhuying.testH5;

import java.io.IOException;

import com.zhuying.DataProvider.getData;
import com.zhuying.DriverProvider.getDriver;
import com.zhuying.WebModuleParts.loginUI;
import com.zhuying.screenShot;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
测试登录功能
 */
public class TestLogin {
    static final Logger logger = Logger.getLogger(TestLogin.class.getName());
    private WebDriver driver=null;
    private int i = 0;

    //测试数据
    @DataProvider(name = "userTable")
    public Object[][] Users() throws IOException {

        getData gd = new getData();
        return gd.getXlsData("F:\\JavaIdea\\TestZMF\\src\\main\\resources", "testH5.xlsx", "Login");
//        return gd.getCsvDate("F:\\JavaIdea\\TestZMF\\src\\main\\resources\\testH5Login.csv");

//        return new Object[][]{
//                {"",""},
//                {"","zhuying"},
//                {"13588057343",""},
//                {"13566747752","zhuying"},
//                {"13588057343","123456"},
//                {"13588057343","zhuying"},
//        };
    }


    @BeforeClass(alwaysRun = true)
    public void setUp() {
        logger.info("# # # # # # # # # # # 测试登录# # # # # # # # # # # # # # # # ");
        getDriver gd = new getDriver();
//        driver = gd.linkChrome();
        driver=gd.linkFirefox();
    }

    @Test(dataProvider="userTable")
    public void testLogin(String userName,String password,String tip) throws Exception {
        logger.info("用户名："+userName+"，密码："+password);


        //导航到应用程序
//        driver.get(Constant.MAIN_ADDR);
//        logger.info("已获取地址栏信息");

        //加载网址，输入数据
        loginUI loginui=new loginUI(driver);
        loginui.load("https://to.zhongmafu.com:30443/h5/");
        loginui.putData("mobilePhone",userName,"userPassword",password,"//button");

//        //输入数据
//        try {
//            //从输入框清空内容
//            driver.findElement(By.name(Constant.LOGIN_YHM)).clear();
//            //输入一些文本输入框
//            driver.findElement(By.name(Constant.LOGIN_YHM)).sendKeys(userName);
//            logger.info("用户名已输入.");
//            driver.findElement(By.name(Constant.LOGIN_MM)).clear();
//            driver.findElement(By.name(Constant.LOGIN_MM)).sendKeys(password);
//            logger.info("密码已输入.");
//            driver.findElement(By.xpath(Constant.LOGIN_BUTTON)).click();
//            logger.info("登陆按钮已点击.");
//            Thread.sleep(1000);
//
//
//
//        }catch(Exception e1){
//            logger.error("未发现文本框.");
//            logger.error(e1);
//        }

        //截图
        screenShot screenshot = new screenShot(driver);
        screenshot.makeShot("F:\\screenshots\\","login_test",i);
        i++;
//        try{
//            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//            String si=String.valueOf(i);
//            FileUtils.copyFile(screenshot,new File(Constant.SCREEN_SHOT+"login_test"+si+".jpg"));
//            logger.info("------测试"+si+"截屏成功.--------");
//            i++;
//        } catch (Exception e) {
//            logger.error("截屏失败：");
//            logger.error(e);
//        }

    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        //	Quits the driver and closes all the associated window of that driver.
        driver.close();
        logger.info("已退出.");
        logger.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
    }


}
