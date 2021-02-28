package com.gears42.surelock.pages;
import com.gears42.surelock.properties.TestProperties;
import com.gears42.surelock.util.WebDriverCommonLib;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

public abstract class BasePage extends WebDriverCommonLib {

    public AppiumDriver<WebElement> driver;


    public BasePage(AppiumDriver<WebElement> driver){
        PageFactory.initElements(driver, this);
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(TestProperties.INSTANCE.IMP_WAIT, TimeUnit.SECONDS);
    }

    public static void sleep(int a) throws InterruptedException{
        a=a*1000;
        Thread.sleep(a);
    }

    public void scrollTo(String string) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + string + "\").instance(0))")).isDisplayed();
        Reporter.log("Scrolling till : " + string, true);
    }

    public void goBack() throws InterruptedException, IOException{
        sleep(1);
        Runtime.getRuntime().exec("adb "+" shell input keyevent 4");
        sleep(1);
        Reporter.log("Tapped on Back Button", true);
    }

    public void launchSureLock() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+" shell am start -a android.intent.action.MAIN -n "+ TestProperties.INSTANCE.PACKAGE_ID +"/.ClearDefaultsActivity");
        sleep(4);
        Reporter.log("Launched SureLock", true);
    }
}