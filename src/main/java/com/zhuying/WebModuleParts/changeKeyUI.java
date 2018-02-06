package com.zhuying.WebModuleParts;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class changeKeyUI {
    static final Logger logger = Logger.getLogger(changeKeyUI.class.getName());
    private WebDriver driver=null;

    public changeKeyUI(WebDriver driver) {
        this.driver = driver;
    }

    //输入的数据：原密码文本框、原密码、新1文本框、新1、新2文本框、新2、确定按钮
    public void putData(String oldTxt,String old,String new1Txt,String new1,String new2Txt,String new2,String yesBtn){
        //输入数据
        try {


            driver.findElement(By.name(oldTxt)).clear();
            driver.findElement(By.name(old)).sendKeys(old);
            logger.info("原密码-已输入");

            driver.findElement(By.name(new1Txt)).clear();
            driver.findElement(By.name(new1)).sendKeys(new1);
            logger.info("新密码-已输入");

            driver.findElement(By.name(new2Txt)).clear();
            driver.findElement(By.name(new2)).sendKeys(new2);
            logger.info("确认密码-已输入");

            //*****采用js可以避免js的一些变化！！！执行js脚本，采用js实现点击
            JavascriptExecutor js = (JavascriptExecutor) driver;

            //判断yesBtn是xpath还是name。
            if(yesBtn.indexOf("//")<0){
                //找不到字符串“//”则为name
                js.executeScript("arguments[0].click();",driver.findElement(By.name(yesBtn)));
//                driver.findElement(By.name(yesBtn)).click();
            }
            else {
                //否则为xpath
                js.executeScript("arguments[0].click();",driver.findElement(By.xpath(yesBtn)));
//                driver.findElement(By.xpath(yesBtn)).click();
            }
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
//            driver.findElement(By.xpath("//button[@type='submit']")).click();
            logger.info("确定-已点击");

        } catch (Exception e) {
            driver.close();
            logger.error("点击异常.");
            logger.error(e);
        }
    }
}
