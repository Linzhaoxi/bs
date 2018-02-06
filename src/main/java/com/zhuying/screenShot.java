package com.zhuying;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class screenShot {
    static final Logger logger = Logger.getLogger(screenShot.class.getName());
    private WebDriver driver=null;


    public screenShot(WebDriver driver) {
        this.driver = driver;
    }

    //截图参数：地址、文件名、默认的起始编号
    public void makeShot(String Path, String name,int i){
        //截图
        try{
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String si=String.valueOf(i);
            FileUtils.copyFile(screenshot,new File(Path+name+si+".jpg"));
            logger.info("------测试"+si+"截屏成功.--------");
//            i++;
        } catch (Exception e) {
            logger.error("截屏失败：");
            logger.error(e);
        }
    }
}
