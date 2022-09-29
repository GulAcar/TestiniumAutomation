package org.example.test;

import static org.example.constants.Constants.*;
import static org.example.ShopPageData.ShopData.*;
import static org.example.csvReader.readCSV;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.example.base.BaseTest;
import org.example.constants.Constants;
import org.example.page.ShopPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.io.FileNotFoundException;

public class ShopPageTest extends BaseTest {
    ShopPage shopPage;


    ExtentHtmlReporter htmlReporter;

    ExtentReports extent;
    //helps to generate the logs in the test report.
    ExtentTest test;


    @Before
    public void before(){
        shopPage =new ShopPage(getWebDriver());

        // initialize the HtmlReporter
        htmlReporter = new ExtentHtmlReporter("/Users/kaanboldan/Desktop/testiniumAutomation/src/reports/ExtentReport.html");

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);


        //configuration items to change the look and feel
        //add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Simple Automation Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        //htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

    }
    @Test
    public void test1() throws InterruptedException {
        test = extent.createTest("Test Case", "Network Sayfasında gezilmesi");


        String[] userInfo;
        try {
            userInfo = readCSV();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(shopPage.getUrl(), SiteLinki);
        shopPage.sendKeys(aramaButonu, ceket);
        shopPage.sendKeys(aramaButonu);
        shopPage.click(sonrakiSayfaBtn);
        Assert.assertEquals(shopPage.getUrl(), ikinciSayfaLinki);
        shopPage.click(siralamaDM);
        shopPage.click(indirimSiralamaBtn);
        ((JavascriptExecutor)getWebDriver()).executeScript("document.body.style.zoom='70%';");
        shopPage.hoverElement(ilkUrunBtn);
        shopPage.click(bedenSecBtn);
        shopPage.click(sepeteGitBtn);
        Assert.assertEquals(shopPage.getText(bedenTxt),beden);
        Assert.assertEquals(shopPage.getText(fiyatTxt),fiyat);
        Boolean A=(Double.parseDouble(shopPage.getText(fiyatTxt).replaceAll(" TL","").replace(".","").replace(",","."))
                >Double.parseDouble(shopPage.getText(indirimFiyatTxt).replaceAll(" TL","").replace(".","").replace(",","")));
        Assert.assertFalse(A);

        Assert.assertEquals(shopPage.getUrl(), sepetBilgiLinki);
        shopPage.click(devamEtBtn);
        shopPage.sendKeys(mailTxt, userInfo[0]);
        shopPage.sendKeys(sifreTxt, userInfo[1]);
        Assert.assertEquals(shopPage.getText(girisYapBtn),giris);
        shopPage.click(girisYapBtn);
        shopPage.click(networkLogo);
        shopPage.click(sepetBtn);
        Assert.assertEquals(shopPage.getText(sepetBaslikTxt), sepet);
        shopPage.click(copBtn);
        shopPage.click(urunSilOnayBtn);
        shopPage.click(sepetBtn);
        Assert.assertEquals(shopPage.getText(sepetBosTxt), sepetbos);
    }



    @After
    public void after()
    {
        tearDown();

    }
}
