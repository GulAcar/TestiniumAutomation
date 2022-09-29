package org.example.base;

import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    static WebDriver webDriver =null;

    @Before
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-notifications");
        options.addArguments("disable-popup-blocking");
        setWebDriver(new ChromeDriver(options));
        getWebDriver().navigate().to("https://www.network.com.tr/");
        getWebDriver().manage().window().maximize();









    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }

    public static void setWebDriver(WebDriver webDriver){
        BaseTest.webDriver=webDriver;
    }

    public void tearDown(){
        getWebDriver().quit();
    }
}
