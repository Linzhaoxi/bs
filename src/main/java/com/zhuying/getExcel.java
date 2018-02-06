package com.zhuying;

import com.zhuying.DataProvider.getData;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class getExcel {
    static final Logger logger = Logger.getLogger(getExcel.class.getName());
    @DataProvider(name="testData")
    public Object[][] words() throws IOException
    {
        getData gd = new getData();
        return gd.getXlsData("F:\\JavaIdea\\TestZMF\\src\\main\\resources","testH5.xlsx","PayKey");

//        return new Object[][]{
//                {"1","1","1"},
//                {"2","2","2"},
//        };
    }

    @Test(dataProvider="testData")
    public void searchTest(String word1,String word2,String word3,String w4){
        logger.info("已获取地址栏信息.");
        System.out.println(word1+word2+word3);
    }
}
