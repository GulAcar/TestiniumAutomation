package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver=null;
    WebDriverWait wait=null;

    public BasePage(WebDriver driver)
    {
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public WebElement findElement(By by)
    {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElement(by);
    }

    public void sendKeys(By by,String text)
    {
        findElement(by).sendKeys(text);
    }

    public void sendKeys(By by)
    {
        findElement(by).sendKeys(Keys.RETURN);
    }
    public void click(By by)
    {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        findElement(by).click();
    }

    public void hoverElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(by)).build().perform();
    }

    public String getText(By by)
    {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return findElement(by).getText();
    }
    public String getUrl(){
        return driver.getCurrentUrl();
    }
}
