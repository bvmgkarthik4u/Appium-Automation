package com.gears42.surelock.util;

import com.gears42.surelock.base.BaseTestInitialization;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

public class TestUtil {

    public static void takeScreenshotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot) BaseTestInitialization.mAppiumDriver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
    }

    public void adbDevices() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb devices");
        sleepThread(2);
        Reporter.log("adb devices", true);
    }

    public void sleepThread(int a) throws InterruptedException {
        a = a * 1000;
        Thread.sleep(a);
    }
}
