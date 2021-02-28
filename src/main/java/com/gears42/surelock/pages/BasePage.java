package com.gears42.surelock.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class BasePage {
    public static AppiumDriver<WebElement> driver;

    public BasePage(AppiumDriver<WebElement> driver){
        PageFactory.initElements(driver, this);
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


}
