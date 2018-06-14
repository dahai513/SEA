package com.toolbar.util;

import com.toolbar.model.TissotInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

 import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Json {

//获取信息
    public static String tissotInfo(String id) throws InterruptedException {
        System.setProperty( "webdriver.gecko.driver", "C:\\Program Files\\Mozilla Firefox\\geckodriver.exe" );
        WebDriver driver = new FirefoxDriver();
        driver.get( "https://macan.alibaba-inc.com/selfCheck/startSingleSelfCheckJobBySellerCodeAndBusinessTypes?_input_charset=utf-8&sellerCode="+id+"&callback=reqwest_1520910049023" );
        WebElement user = driver.findElement( By.name( "domainAccount" ) );
        user.sendKeys( "wb-xdh313909" );
        WebElement pass = driver.findElement( By.name( "password" ) );
        pass.sendKeys( "Xudahai513" );
        WebElement btn = driver.findElement( By.className( "sso-btn-submit" ) );
        Thread.sleep( 1000 );
        btn.click();
        Thread.sleep( 1000 );

        WebElement copy = driver.findElement( By.id( "tab-1" ) );
        copy.click();
        Thread.sleep( 1000 );
        WebElement data = driver.findElement( By.className( "data" ) );
        String info =  data.getText();
        driver.close();
        String str = info.split( "天梭商家线下商品情况" )[1];
        return str;
    }
//校验信息
    public static int json(String str,String rstr){
        String reg = "[0-9]{0,6}"+rstr;
        String tt = "[0-9]{0,6}";

        Pattern p = Pattern.compile( reg );
        Matcher m = p.matcher( str );

        Pattern p_int = Pattern.compile( tt );

        while (m.find()){
            Matcher m_int = p.matcher( m.group( 0 ) );
            while (m_int.find()){
                return Integer.parseInt( m_int.group( 0) );
            }
         }
        return 0;
    }

//提取信息
    public static TissotInfo info(String id) throws InterruptedException {
        TissotInfo tissotInfo = new TissotInfo();
        String str = tissotInfo(id);
        tissotInfo.setItem(  json( str,"个线下商品" ));
        tissotInfo.setSku(  json( str,"个sku记录" ));
        tissotInfo.setTotal_store(  json( str, " 个门店" ));
        tissotInfo.setOpen_store( json( str,"个开启") );
        return tissotInfo;
    }

}
