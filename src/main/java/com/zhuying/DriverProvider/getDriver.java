package com.zhuying.DriverProvider;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class getDriver {
    static final Logger logger = Logger.getLogger(getDriver.class.getName());
    private WebDriver driver=null;

    public WebDriver linkChrome(){
        logger.info("---------------开始启动Chrome.--------------------");
        try {
            //设置Chrome的驱动地址
            System.setProperty("webdriver.chrome.driver","F:\\JavaIdea\\TestZMF\\tools\\chromedriver.exe");
            driver = new ChromeDriver();
            //此处设置的等待时间 是针对全局设置的，webdriver中执行所有命令的超时时间都设置为30秒了，
            // 如以后的findElement方法，找不到元素会默认等待三十秒。
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("Chrome浏览器启动失败");
            logger.error(e);
        }
        return driver;
    }

    public WebDriver linkFirefox(){
        logger.info("---------------开始启动Firefox.--------------------");
        try {
            //指向firefox.exe执行文件
            System.setProperty("webdriver.firefox.bin","E:\\Program Files\\Mozilla Firefox\\firefox.exe");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("Firefox浏览器启动失败");
            logger.error(e);
        }
        return driver;
    }


}
