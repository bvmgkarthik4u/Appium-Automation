package com.gears42.surelock.util;

import com.gears42.surelock.base.BaseTestInitialization;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverCommonLib {

    public void waitForPageToLoad() {
        BaseTestInitialization.mAppiumDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void waitForXpathPresent(String wbXpath, int waitTime) {
        WebDriverWait wait = new WebDriverWait(BaseTestInitialization.mAppiumDriver, waitTime);
        wait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.xpath(wbXpath)));
    }

    public void waitForClassNamePresent(String wbName, int waitTime) {
        WebDriverWait wait = new WebDriverWait(BaseTestInitialization.mAppiumDriver, waitTime);
        wait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.name(wbName)));
    }

    public void waitForidPresent(String wbid, int waitTime) {
        WebDriverWait wait = new WebDriverWait(BaseTestInitialization.mAppiumDriver, waitTime);
        wait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.id(wbid)));
    }

    public void waitForVisibilityOf(String wbxpath, int waitTime) {
        WebDriverWait wait = new WebDriverWait(BaseTestInitialization.mAppiumDriver, waitTime);
        wait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.xpath(wbxpath)));
    }

    public void waitForElementPresent(String wbXpath) {
        WebDriverWait wait = new WebDriverWait(BaseTestInitialization.mAppiumDriver, 180);
        wait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.xpath(wbXpath)));
    }

    public void waitForLogXpathPresent(String wbXpath) {
        WebDriverWait wait = new WebDriverWait(BaseTestInitialization.mAppiumDriver, 180);
        wait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.xpath(wbXpath)));
    }

    public static void sleep(int time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }
}
