package com.gears42.surelock.pages;
import com.gears42.surelock.properties.TestProperties;
import com.gears42.surelock.util.AssertLib;
import com.gears42.surelock.util.WebDriverCommonLib;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.gears42.surelock.properties.TestProperties.*;
import static com.gears42.surelock.properties.TestProperties.PACKAGEID;
import static java.time.Duration.ofSeconds;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.IIOImage;

import org.apache.commons.io.FileUtils;
import org.eclipse.jetty.websocket.client.io.ConnectionManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.io.Connection;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.LocksDevice;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
//import static io.appium.java_client.touch.LongPressOptions.
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.HasSupportedPerformanceDataType;
import io.appium.java_client.android.SupportsNetworkStateManagement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
//import net.sourceforge.tess4j.ITesseract;
import io.appium.java_client.MobileElement;
import static io.appium.java_client.touch.TapOptions.tapOptions;

/*
Modified Elements :
Modified Methods  :
Added Methods 	: tapOnTheFeature(), tapToEnableTheFeature(), tapToDisableTheFeature(), verifyCheckBoxIsDisabledOf(), verifyCheckBoxIsEnabledOf(), verifyOSVersionIs10AndAbove(), verifyOSVersionIsGreaterThanProvided(), isGrayedOut(), UnlockingDeviceThroughPassword(), lonPressKeyCode(), verifyPowerOff(), verifyDeviceDoesNotSupportsKnox(), verifyEAIsNotInstalledOnDevice(), verifyWidgetTrayPositionOnSureLockHomeScreen()
*/

public abstract class BasePage extends WebDriverCommonLib {

    public static AppiumDriver<WebElement> driver;
    public static WebDriver chromedriver;

    Dimension size;
    static String destDir;
    static DateFormat dateFormat;

    @FindBy(xpath="//android.widget.Button[@text='OK' or @text='Ok' or @text='ok']")
    public WebElement OkBtn;

    @FindBy(xpath="//android.widget.Button[@text='Set' or @text='SET']")
    private WebElement SetBtn;

    @FindBy(xpath="//android.widget.Button[@text='CANCEL' or @text='Cancel' or @text='cancel']")
    public WebElement CancelBtn;

    @FindBy(xpath="//android.widget.TextView[@text='CANCEL' or @text='Cancel']")
    public WebElement CancelText;

    @FindBy(id=""+ PACKAGEID+":id/closeDL")
    public WebElement passwordCloseBtn;

    @FindBy(xpath="//android.widget.Button[@text='NO' or @text='No']")
    private WebElement NoBtn;

    @FindBy(id="com.samsung.klmsagent:id/eula_bottom_confirm_agree")
    private WebElement KLMSAgenConfirmBtn;

    @FindBy(xpath="//android.widget.Button[contains(@text,'ACTIVATE') or contains(@text, 'Activate')]")
    private WebElement AdminActivateSL;

    @FindBy(id="com.android.settings:id/action_button")
    private WebElement AdminActivateNix;

    @FindBy(xpath="//android.widget.Button[@text='Proceed' or @text='PROCEED' or @text='proceed']")
    private WebElement ProceedBtn;

    @FindBy(id="android:id/edit")
    private WebElement TextField;

    @FindBy(xpath="//android.widget.Button[@text='Done' or @text='DONE']")
    public WebElement DoneBtn;

    @FindBy(xpath="//android.widget.Button[@text='Save' or @text='SAVE']")
    private WebElement SaveBtn;

    @FindBy(xpath="//android.widget.Button[@text='Add' or @text='ADD']")
    private WebElement AddBtn;

    @FindBy(xpath="//android.widget.Button[@text='Remove' or @text='REMOVE']")
    private WebElement RemoveBtn;

    @FindBy(xpath="//android.widget.Button[@text='Overwrite' or @text='OVERWRITE' or @text='overwrite' or OverWrite]")
    private WebElement OverwriteBtn;

    //	@FindBy(xpath="//android.widget.Button[@text='I have read and agree to all the terms and conditions above.']")
    @FindBy(id="com.samsung.klmsagent:id/checkBox1")
    private WebElement KLMSAgent;

    @FindBy(xpath="//android.widget.Button[@text='Always' or @text='ALWAYS']")
    public static WebElement Alwaysbtn;

    @FindBy(xpath="//android.widget.TextView[@text='Download']")
    private WebElement DownloadEsExpl;

    @FindBy(xpath="//android.widget.TextView[@text='DCIM']")
    private WebElement DCIMEsExpl;

    @FindBy(xpath="//android.widget.TextView[@text='Admin Settings']")
    protected WebElement adminSettings;

    @FindBy(xpath="//android.widget.Button[@text='YES' or @text='Yes' or @text='yes']")
    public WebElement Yesbtn;

    @FindBy(id="com.samsung.android.samsungpassautofill:id/close_icon")
    public WebElement SamsungPassclose;

    @FindBy(id="android:id/edit")
    public WebElement EditOption;

    @FindBy(id=""+ PACKAGEID+":id/password_edit")
    public WebElement sureLockPassword;

    @FindBy(xpath="//android.widget.EditText")
    public WebElement EditTextOption;

    @FindBy(xpath="//android.widget.Button[@text='UNCAUGHTEXCEPTION']")
    public WebElement UNCAUGHTEXCEPTION;

    @FindBy(id="android:id/alertTitle")
    public WebElement alertTitle;

    @FindBy(xpath="//android.widget.Button[@text='Wait' or @text='WAIT' or @text='wait']")
    public WebElement waitBtn;

    @FindBy(xpath="//android.widget.Button[@text='Open app again']")
    public WebElement OpenAppAgain;

    @FindBy(xpath="//android.widget.Button[@text='Close app' or @text='CLOSE APP' or @text='close app' or @text='close' or @text='Close']")
    public WebElement closeApp;

    @FindBy(xpath="//android.widget.TextView[@text='SureLock']")
    protected WebElement SureLockText;

    @FindBy(xpath="//android.widget.TextView[@text='Battery Popup Notification']")
    private WebElement BatteryPopupNotification;

    @FindBy(xpath="//android.widget.TextView[@text='Hide Quick Settings Tiles']")
    private WebElement HideQuickSettingsTiles;

    @FindBy(xpath="//android.widget.TextView[@text='Requires rooting/Platform Permission (from Lollipop and above)']")
    private WebElement RequiresRootingPlatformPermissionFromLollipopAndAbove;


    public void verifyCrash() throws IOException {
        WebDriverWait wait=new WebDriverWait(driver, 10);
        unlockDevice();
        osVersion();
        System.out.println(os);
        if(os.contains("9") || os.contains("10") || os.contains("5")) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'SureLock isn')]")));
                System.out.println("Crash");
                String crashMsg = driver.findElementByXPath("//android.widget.TextView[contains(@text,'SureLock isn')]").getText();
                System.out.println(crashMsg);
                boolean value = false;
                try {
                    value = waitBtn.isDisplayed();
                } catch (Exception e) {
                    System.out.println(e);
                }
                String SLCrash = "SureLock isn't responding";
                String SLCrashTrim = crashMsg.substring(0, 25);
                if(SLCrashTrim.contains(SLCrash) || value==true)
                {
                    File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                    String filename=UUID.randomUUID().toString();
                    FileUtils.copyFile(file, new File(".\\AppCrash\\"+filename+".jpg"));
                    closeApp.click();
                    sleep(10);
                    launchSureLock();
                    sleep(5);
                    Assert.fail("SureLock isn't responding is displayed");
                }
            } catch (Exception e) {
                Reporter.log("PASS >>>> ANR is not displayed", true);
            }
        }
        else if(os.contains("8") || os.contains("7")){
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'stopped')]")));
                System.out.println("Crash");
                String crashMsg1 = driver.findElementByXPath("//android.widget.TextView[contains(@text,'SureLock has stopped')]").getText();
                System.out.println(crashMsg1);
                boolean value1 = false;
                try {
                    value1 = OpenAppAgain.isDisplayed();
                } catch (Exception e) {
                    System.out.println(e);
                }
                String SLCrash1 = "SureLock has stopped";
                String SLCrashTrim1 = crashMsg1.substring(0, 25);
                if(SLCrashTrim1.contains(SLCrash1) || value1==true)
                {
                    File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                    String filename=UUID.randomUUID().toString();
                    FileUtils.copyFile(file, new File(".\\AppCrash\\"+filename+".jpg"));
                    closeApp.click();
                    sleep(10);
                    launchSureLock();
                    sleep(5);
                    Assert.fail("SureLock has stopped is displayed");
                }
            } catch (Exception e) {
                Reporter.log("PASS >>>> ANR is not displayed", true);
            }
        }
        else {

        }
    }


    public BasePage(AppiumDriver<WebElement> driver){
        PageFactory.initElements(driver, this);
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(TestProperties.INSTANCE.IMPWAIT, TimeUnit.SECONDS);
    }

/*	private void raiseToast(String text) {
	    ImmutableMap<String, Object> scriptArgs = ImmutableMap.of(
	        "target", "application",
	        "methods", Arrays.asList(ImmutableMap.of(
	            "name", "raiseToast",
	            "args", Arrays.asList(ImmutableMap.of(
	                "value", text,
	                "type", "String"
	            ))
	        ))
	    );

	    driver.executeScript("mobile: backdoor", scriptArgs);
	}
	ImmutableMap<String, Object> args = ImmutableMap.of(
	    "text", "toast text to match",
	    "isRegexp", false
	);

	driver.executeScript("mobile: isToastVisible", args);

	public static ExpectedCondition<Boolean> toastMatches(String matchText, Boolean isRegexp) {
	    return new ExpectedCondition<Boolean>() {
	        @Override
	        public Boolean apply(WebDriver driver) {
	            ImmutableMap<String, Object> args = ImmutableMap.of(
	                "text", matchText,
	                "isRegexp", isRegexp
	            );
	            return (Boolean) ((JavascriptExecutor)driver).executeScript("mobile: isToastVisible", args);
	        }

	        @Override
	        public String toString() {
	            return "toast to be present";
	        }
	    };
	}*/



    public void enterEditOption(String text) throws InterruptedException{
        EditOption.click();
        EditOption.clear();
        EditOption.sendKeys(text);
        Reporter.log("Entered text in edit field "+ text, true);
    }

    public void enterEditTextOption(String text) throws InterruptedException{
        EditTextOption.click();
        EditTextOption.clear();
        EditTextOption.sendKeys(text);
        Reporter.log("Entered text in edit field "+ text, true);
    }

    public void tapOnOkBtn() throws InterruptedException{
        OkBtn.click();
        Reporter.log("Tapped on Ok Button", true);
        sleep(2);
    }

    public void tapOnUNCAUGHTEXCEPTION() throws InterruptedException{
        UNCAUGHTEXCEPTION.click();
        Reporter.log("Tapped on UNCAUGHTEXCEPTION Button", true);
        sleep(2);
    }

    public void tapOnYesbtn() throws InterruptedException{
        Yesbtn.click();
        Reporter.log("Tapped on Yes Button", true);
        sleep(2);
    }

    public void tapOnAlwaysbtn() throws InterruptedException{
        Alwaysbtn.click();
        Reporter.log("Tapped on Always Button", true);
        sleep(2);
    }

    public void verifyOKButton() {
        OkBtn.isDisplayed();
        Reporter.log("Ok Button is Displayed", true);
    }

    public void tapOnSetBtn(){
        SetBtn.click();
        Reporter.log("Tapped on Set Btn", true);
    }

    public void tapOnCancelBtn() throws InterruptedException{
        CancelBtn.click();
        Reporter.log("Tapped on Cancel Button", true);
    }

    public void tapOnNoButton() throws InterruptedException{
        NoBtn.click();
        Reporter.log("Tapped on No Button", true);
    }

    public void verifyAndTapCancelButton() {
        try {
            CancelBtn.click();
            Reporter.log("Cancel Button is displayed", true);
        }
        catch(Exception e) {
            System.out.println("Cancel button is not displayed");
        }
    }

    public void verifyCancelButton() {
        try {
            CancelBtn.isDisplayed();
            Reporter.log("PASS >> Cancel Button is displayed", true);
        }
        catch (Exception e) {
            AssertLib.AssertFailMethod("Cancel Button is not displayed");
        }
    }

    public void verifyAndTapOkButton() {
        try {
            OkBtn.click();
            Reporter.log("Tapped on Ok Button", true);
            sleep(2);
        }
        catch(Exception e) {
            System.out.println("Knox and EA device : Ok button is not displayed");
        }
    }

    public void verifyAndTapCancelTextButton() {
        try {
            CancelBtn.click();
            Reporter.log("Cancel Button is displayed", true);
            waitForXpathPresent("//android.widget.Button[@text='CANCEL' or @text='Cancel' or @text='cancel']",60);
            CancelBtn.click();
        }
        catch(Exception e) {
            try {
                CancelText.click();
                Reporter.log("Cancel text button is displayed", true);
            } catch (Exception e2) {
                System.out.println("Cancel button is not displayed");
            }
        }
    }

    public void unlockDevice() throws IOException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+"  shell input keyevent 224"); //adb shell input keyevent 82 && adb shell input keyevent 26
        Reporter.log("unlocked the device", true);
    }

    public void verifyAndTapBackButton(String adb) throws InterruptedException, IOException {
        try {
            DoneBtn.isDisplayed();
            Reporter.log("Done button is displayed", true);
        }
        catch(Exception e) {
            unlockDevice();
            goBack();
        }
    }

    public void tapOnKLMSConfirmBtn() throws InterruptedException{
        KLMSAgenConfirmBtn.click();
        Reporter.log("Tapped on Confirm Button", true);
    }

    public void enterIntoTextField(String str){
        TextField.click();
        TextField.clear();
        TextField.sendKeys(str);
        Reporter.log("Entered Into Text Field: " + str, true);
    }

    public void tapOnDoneBtn(){
        DoneBtn.click();
        Reporter.log("Tapped on Done Button", true);
    }

    public void tapOnDoneBtn3Times(){
        DoneBtn.click();
        Reporter.log("Tapped on Done Button", true);
        tapOnDoneBtn();
        tapOnDoneBtn();
    }

    public void tapOnDoneBtn2Times() throws InterruptedException{
        DoneBtn.click();
        Reporter.log("Tapped on Done Button", true);
        sleep(4);
        tapOnDoneBtn();
    }

    public void verifyDoneBtn() {
        try {
            DoneBtn.isDisplayed();
            Reporter.log("Done button is displayed", true);
        } catch (Exception e) {
            AssertLib.AssertFailMethod("Done button is not displayed");
        }
    }

    public void tapOnSaveBtn(){
        SaveBtn.click();
        Reporter.log("Tapped on Save Button", true);
    }

    public void tapOnAddBtn(){
        AddBtn.click();
        Reporter.log("Tapped on Add Button", true);
    }

    public void tapOnRemoveBtn(){
        try {
            RemoveBtn.click();
            Reporter.log("Tapped on Remove Button", true);
            sleep(2);
        } catch (Exception e) {
            Reporter.log("Remove btn is not displayed", true);
        }
    }

    public static void sleep(int a) throws InterruptedException{
        a=a*1000;
        Thread.sleep(a);
    }

    public void scrollTo(String string) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + string + "\").instance(0))")).isDisplayed();
        Reporter.log("Scrolling till : " + string, true);
    }

    public void scrollToContains(String string) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + string + "\").instance(0))")).isDisplayed();
        Reporter.log("Scrolling till : " + string, true);
    }

    public static String timeStamp;
    public void currentTimeForEmailID() {
        timeStamp = new SimpleDateFormat("MMddHHmm").format(Calendar.getInstance().getTime());
        System.out.println(timeStamp);
    }

    @FindBy(xpath="//android.widget.TextView[@text='Admin Settings']")
    private WebElement AdminSettings;

    public void TapOnAdminSettingsText() {
        AdminSettings.click();
    }

    public void hideKeyboard() throws InterruptedException{
        driver.hideKeyboard();
        sleep(1);
        Reporter.log("Hide Keyboard", true);
    }

    public void goBack() throws InterruptedException, IOException{
        sleep(1);
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell input keyevent 4");
//		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
//		driver.navigate().back();
        sleep(1);
        Reporter.log("Tapped on Back Button", true);
    }

    public void goBack2Times() throws InterruptedException, IOException{
        goBack();
        goBack();
    }

    public void goBack3Times() throws InterruptedException, IOException{
        goBack();
        goBack();
        goBack();
    }

    public void goBack4Times() throws InterruptedException, IOException{
        sleep(1);
        driver.navigate().back();
        sleep(1);
        Reporter.log("Tapped on Back Button", true);
        goBack();
        goBack();
        goBack();
    }

	/*public void changeScreenToPortrait(){
		driver.rotate(ScreenOrientation.PORTRAIT);
		Reporter.log("Changed Screen Orientation to PORTRAIT", true);
	}

	public void changeScreenToLandscape(){
		driver.rotate(ScreenOrientation.LANDSCAPE);
		Reporter.log("Changed Screen Orientation to LANDSCAPE", true);
	}*/

    public String getScreenOrientation(){
        ScreenOrientation orientation= driver.getOrientation();
        String status = orientation.value();
        Reporter.log("Screen Orientation : " + status, true);
        return status;
    }

    public String getScreenOrientationUsingADBFor360() throws IOException{
        Process p=Runtime.getRuntime().exec("adb shell settings get system user_rotation");
        BufferedReader output = getOutput(p);
        String userRotation = "";
        userRotation = output.readLine();
        System.out.println(userRotation);
        return userRotation;
    }

    @SuppressWarnings("rawtypes")
    public void pressHome() throws InterruptedException, IOException{
        sleep(2);
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+"shell input keyevent KEYCODE_HOME");
        sleep(3);
        Reporter.log("Pressed Home button", true);
    }

    public void batteryReset()throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell dumpsys battery reset");
        Reporter.log("Battery reset done", true);
    }

    public void pressRescent()throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+"  shell input keyevent KEYCODE_APP_SWITCH");
        sleep(2);
        Reporter.log("Pressed Rescent Button", true);
    }

    public void launchWifiSettings() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+"  shell am start -n com.android.settings/.wifi.WifiSettings");
        sleep(2);
        Reporter.log("Launched WiFi Settings", true);
    }

    public void turnOnWifi() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell svc wifi enable");
        sleep(2);
        Reporter.log("Wifi is turned on", true);
    }

    public void turnOffWifi() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell svc wifi disable");
        sleep(2);
        Reporter.log("Wifi is turned off", true);
    }

    public void turnOnMobileData() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell svc data enable");
        sleep(4);
        Reporter.log("Mobile data is turned on", true);
    }

    public void turnOffMobileData() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell svc data disable");
        sleep(4);
        Reporter.log("Mobile data is turned off", true);
    }

    public String getMobileDataIsOnOrOff() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings get global mobile_data");
        BufferedReader output = getOutput(p);
        String mobileData = "";
        mobileData = output.readLine();
        System.out.println(mobileData);
        return mobileData;
    }



    public void verifyMobileDataIsOn() throws IOException, InterruptedException {
        String status = getMobileDataIsOnOrOff();
        if (status.contains("1")){
            Reporter.log("PASS >>> Mobile data is in ON Status", true);
        }
        else {
            Assert.fail("Mobile data is not in On status");
        }
    }

    public void verifyMobileDataIsOff() throws IOException, InterruptedException {
        String status = getMobileDataIsOnOrOff();
        if (status.contains("0")){
            Reporter.log("PASS >> Mobile data is in OFF Status", true);
        }
        else {
            Assert.fail("Mobile data is not in OFF status");
        }
    }

    public String getKnoxIsOnOrOff() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("adb shell getprop ro.config.knox");
        BufferedReader output = getOutput(p);
        String knox = "";
        knox = output.readLine();
        System.out.println(knox);
        return knox;
    }

    public String getWifiIsOnOrOff() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings get global wifi_on");
        BufferedReader output = getOutput(p);
        String wifi = "";
        wifi = output.readLine();
        System.out.println(wifi);
        return wifi;
    }

    public void verifyWifiIsOn() throws IOException, InterruptedException {
        String status = getWifiIsOnOrOff();
        if (status.contains("1")){
            Reporter.log("PASS >>> Wi-fi is in ON Status", true);
        }
        else {
            Assert.fail("Wi-fi is not in On status");
        }
    }

    public void verifyWifiIsOff() throws IOException, InterruptedException {
        String status = getWifiIsOnOrOff();
        if (status.contains("0")){
            Reporter.log("PASS >> Wi-fi is in OFF Status", true);
        }
        else {
            Assert.fail("Wi-fi is not in OFF status");
        }
    }

    public void turnOnAirplaneMode() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put global airplane_mode_on 1");
        sleep(4);
        Reporter.log("Airplane Mode is turned on", true);
    }

    public void turnOffAirplaneMode() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put global airplane_mode_on 0");
        sleep(4);
        Reporter.log("Airplane Mode is turned off", true);
    }

    public String getAirplaneModeIsOnOrOff() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings get global airplane_mode_on");
        BufferedReader output = getOutput(p);
        String AirplaneMode = "";
        AirplaneMode = output.readLine();
        System.out.println(AirplaneMode);
        return AirplaneMode;
    }

    public void verifyAirplaneModeIsOn() throws IOException, InterruptedException {
        String status = getAirplaneModeIsOnOrOff();
        if (status.contains("1")){
            Reporter.log("PASS >>> Airplane Mode is in ON Status", true);
        }
        else {
            Assert.fail("Airplane Mode is not in On status");
        }
    }

    public void verifyAirplaneModeIsOff() throws IOException, InterruptedException {
        String status = getMobileDataIsOnOrOff();
        if (status.contains("0")){
            Reporter.log("PASS >> Mobile data is in OFF Status", true);
        }
        else {
            Assert.fail("Airplane Mode is not in OFF status");
        }
    }

    public String getBottomBarStatus() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings get global policy_control");
        BufferedReader output = getOutput(p);
        String mobileData = "";
        mobileData = output.readLine();
        System.out.println(mobileData);
        return mobileData;
    }

    public void verifyBottomBarIsEnable() throws IOException, InterruptedException {
        String status = getBottomBarStatus();
        if (status.contains("immersive.navigation=*") || status.contains("-com.sec.android.app.launcher")){
            Reporter.log("PASS >>> Bottom Bar is in ON Status", true);
        }
        else {
            Assert.fail("Bottom Bar is not in On status");
        }
    }

    public void verifyBottomBarIsDisable() throws IOException, InterruptedException {
        String status = getBottomBarStatus();
        if (status.contains("null*")){
            Reporter.log("PASS >> Bottom Bar is in OFF Status", true);
        }
        else {
            Assert.fail("Bottom Bar is not in OFF status");
        }
    }

    public void launchMobileHotSpotTethering() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am start -n com.android.settings/.TetherSettings");
        sleep(2);
        Reporter.log("Launched Mobile HotSpot Tethering", true);
    }


    public void launchBluetoothSettings() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am start -a android.settings.BLUETOOTH_SETTINGS");
        sleep(2);
        Reporter.log("Launched Bluetooth Settings", true);
    }

    public void launchSystemSettings() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am start -a android.settings.SETTINGS");
        sleep(2);
        Reporter.log("Launched System Settings", true);
    }

    public void launchSoundSettings() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am start -a android.settings.SOUND_SETTINGS");
        sleep(2);
        Reporter.log("Launched Sound Settings", true);
    }

    @FindBy(xpath="//android.widget.TextView[@text='Sound' or @text='SOUND' ]")
    private WebElement soundOption;

    public void launchDisplaySettings() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am start -a android.settings.DISPLAY_SETTINGS");
        sleep(2);
        Reporter.log("Launched Display Settings", true);
    }

    public void turnOnBluetooth() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am start -a android.bluetooth.adapter.action.REQUEST_ENABLE");
        sleep(2);
        try {
            waitForXpathPresent("//android.widget.Button[@text='Allow' or @text='ALLOW' or @text='allow']", 10);
            driver.findElementByXPath("//android.widget.Button[@text='Allow' or @text='ALLOW' or @text='allow']").click();
            Reporter.log("Bluetooth is Turned On", true);
            sleep(4);
        } catch (Exception e) {
            System.out.println("Allow is not displayed");
        }
    }

    public void turnOffBluetooth() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am start -a android.bluetooth.adapter.action.REQUEST_DISABLE");
        sleep(2);
        try {
            waitForXpathPresent("//android.widget.Button[@text='Allow' or @text='ALLOW' or @text='allow']", 10);
            driver.findElementByXPath("//android.widget.Button[@text='Allow' or @text='ALLOW' or @text='allow']").click();
            Reporter.log("Bluetooth is Turned Off", true);
            sleep(4);
        } catch (Exception e) {
            System.out.println("Allow is not displayed");
        }
    }

    public String getBluetoothStatus() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings get global bluetooth_on");
        BufferedReader output = getOutput(p);
        String Bluetooth = "";
        Bluetooth = output.readLine();
        System.out.println(Bluetooth);
        return Bluetooth;
    }

    public void verifyBluetoothIsOn() throws IOException, InterruptedException {
        String status = getBluetoothStatus();
        if (status.contains("1")){
            Reporter.log("PASS >>> Bluetooth is in ON Status", true);
        }
        else {
            Assert.fail("Bluetooth is not in On status");
        }
    }

    public void verifyBluetoothIsOff() throws IOException, InterruptedException {
        String status = getBluetoothStatus();
        if (status.contains("0")){
            Reporter.log("PASS >> Bluetooth is in OFF Status", true);
        }
        else {
            Assert.fail("Bluetooth is not in OFF status");
        }
    }

    public void turnOnGPS() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put secure location_providers_allowed +gps");
        Reporter.log("GPS is Turned On", true);
        sleep(2);
    }

    public void turnOffGPS() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put secure location_providers_allowed -gps");
        Reporter.log("GPS is Turned Off", true);
        sleep(2);
    }

    public void unpluggedTheBattery() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell dumpsys battery set usb 0");
        Reporter.log("Unplugged the device", true);
    }

    public void pluggedTheBattery() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell dumpsys battery set ac 1");
        Reporter.log("Plugged the device", true);
    }

    public void dischargeBattery() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell dumpsys battery set status 3");
        Reporter.log("Plugged the device", true);
    }

    public void setBatteryLevelToSomePercentage(String batteryLevel) throws IOException {
        Runtime.getRuntime().exec("adb shell dumpsys battery set level "+batteryLevel+"");
        Reporter.log("Battery level to "+batteryLevel, true);
    }

    public void setBatteryLevelTo5Per() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell dumpsys battery set level 5");
        Reporter.log("Battery Level set to 5", true);
    }

    public void resetBatterySettings() throws IOException, InterruptedException {
//		Runtime.getRuntime().exec("adb shell dumpsys battery set usb 1");
//		sleep(2);
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell dumpsys battery reset");
        Reporter.log("Battery reset done", true);
    }

    protected static BufferedReader getOutput(Process p) {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    public String getBrightnessLevel() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings get system screen_brightness");
        BufferedReader output = getOutput(p);
        String ligne = "";
        ligne = output.readLine();
        System.out.println(ligne);
        return ligne;
    }

    public void setBrightnessLevel(int bLevel) throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put system screen_brightness "+bLevel);
        sleep(2);
        Reporter.log("Brightness Level is set : "+bLevel, true);
    }

    public void deviceReboot() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" reboot");
        sleep(2);
        Reporter.log("Device reboot proccessed", true);
    }


    public void adbDevices() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb connect "+ASTROFARMCOMM+"");
        sleep(2);
        Runtime.getRuntime().exec("adb devices");
        sleep(2);
        Reporter.log("adb Devices", true);
    }

    public String getKeyBoardType() throws IOException {
        Process p = Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings get secure default_input_method");
        BufferedReader output = getOutput(p);
        String KeyBoardType = "";
        KeyBoardType = output.readLine();
        System.out.println(KeyBoardType);
        return KeyBoardType;
    }

    public void launchLocationSettings() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am start -a android.settings.LOCATION_SOURCE_SETTINGS");
        sleep(2);
        Reporter.log("Launched Location Settings", true);
    }

    public void activateSureLock() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am broadcast -a "+PACKAGEID+".COMMUNICATOR -e \"command\" \"activate\" -e \"password\" \"0000\" -e \"activation_code\" \"7820539392\" -e \"prefered_activation_id\" \"0\" "+PACKAGEID+"");
        sleep(10);
        Reporter.log("Activated SureLock", true);
    }

    public void setSpeedAndPathForDriverSafety(String path, int value) throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am broadcast -a "+PACKAGEID+".COMMUNICATOR -n "+PACKAGEID+"/"+PACKAGEID+".service.SureLockCommunicator -e \"password\"  \"0000\" -e \"command\" \"handle_driver_safety_profile\" -e \"/mnt/sdcard/SureLock.settings\" \"/mnt/sdcard/SureLock1.settings\" -e \"speed\" \"30\"");
        sleep(2);
        Reporter.log("Path and speed are set", true);
    }

    public void tapOnTapOnscreen5TimesText(int n) throws IOException, InterruptedException{
				/*WebElement activate = driver.findElementByXPath("//android.widget.TextView[@text='Tap the screen 5 times']");
				for (int i = 0; i < n; i++) {
				touchAction.tap((TapOptions) activate).perform();
				Thread.sleep(2000);
				System.out.println(i);*/
        int i = 0;
        while(i<n) {
            WebElement activate = driver.findElementByXPath("//android.widget.TextView[@text='Tap the screen 5 times']");
            activate.click();
            i++;
        }
    }

    public void tapOnpasswordCloseBtn() throws IOException, InterruptedException{
        passwordCloseBtn.click();
        Reporter.log("Tap on password close button", true);
    }

    public void tapOnLoginToAccessYourProfile(int n) throws IOException, InterruptedException{
        int i = 0;
        while(i<n) {
            WebElement activate = driver.findElementByXPath("//android.widget.TextView[@text='Login to access your profile']");
            activate.click();
            i++;
        }
        Reporter.log("Tapped on screen 5 times", true);
    }

    public void tapOnscreenNTimes(int n) throws IOException, InterruptedException{
        int i = 0;
        while(i<n) {
            WebElement activate = driver.findElementById(""+PACKAGEID+":id/gridview");
            activate.click();
            i++;
        }
    }

	/*public void tapOnscreenNTimes(int n) throws IOException, InterruptedException{
		WebElement activate = driver.findElementById(""+PACKAGEID+":id/gridview");
		for(int i=0;i<n;i++){
			activate.click();
		}
		Reporter.log("Tapped on screen "+n+" times", true);
		sleep(2);
	}*/

    public void tapOnscreen5Times(int n) throws IOException, InterruptedException{
        WebElement activate = driver.findElementByXPath("//android.widget.GridView");
        activate.click();
        activate.click();
        activate.click();
        activate.click();
        activate.click();
		/*int i = 0;
		while(i<n) {


		}
		Reporter.log("Tapped on screen "+n+" times", true);*/
    }

    public void tapOnscreen5TimesOnSLImage(int n) throws IOException, InterruptedException{
        int i = 0;
        while(i<n) {
            driver.findElementById(""+PACKAGEID+":id/imageView").click();
            i++;
        }
        Reporter.log("Tapped on screen 5 times", true);
    }

    @FindBy(id=""+PACKAGEID+":id/password_edit")
    public WebElement passwordEdit;

    @FindBy(id=""+PACKAGEID+":id/password")
    public WebElement passwordOption;

    @FindBy(id=""+PACKAGEID+":id/GotoAdminSettings")
    public WebElement GotoAdminSettings;

    @FindBy(id=""+PACKAGEID+":id/exitSL")
    public WebElement exitSL;

    @FindBy(xpath="//android.view.ViewGroup[@index='0']")
    public WebElement AndroidLayout;

    @FindBy(xpath="//android.widget.TextView[@text='Tap the screen 5 times']")
    public WebElement Screen5Times;


    public void EnterThePassword() {
        passwordEdit.click();
        passwordEdit.sendKeys("0000");
        Reporter.log("Entered the Password as: 0000", true);
    }

    public void EnterThePassword(String pwd) {
        passwordEdit.click();
        passwordEdit.sendKeys(pwd);
        Reporter.log("Entered the Password as: "+pwd, true);
    }

    public void EnterThePasswordForTouchEvent(String pwd) {
        passwordOption.click();
        passwordOption.sendKeys(pwd);
        Reporter.log("Entered the Password as: 0000", true);
    }

    public void tapOnGotoAdminSettings() throws InterruptedException {
        GotoAdminSettings.click();
        Reporter.log("Tapped on Go to Admin Settings", true);
        sleep(3);
    }

    public void tapOnExitSureLock() throws InterruptedException {
        exitSL.click();
        Reporter.log("Tapped on Exit SureLock", true);
        sleep(3);
    }

    public void addAppOnHomeScreen(String pkg) throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am broadcast -a "+PACKAGEID+".COMMUNICATOR -e password 0000 -e command add_application -e package_name " + pkg + " "+PACKAGEID+"");
        sleep(2);
        Reporter.log("Added App " + pkg + " on SureLock Homescreen", true);
    }

    public void removeAppOnHomeScreen(String pkg2) throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am broadcast -a "+PACKAGEID+".COMMUNICATOR -e password 0000 -e command remove_application -e package_name " + pkg2 + " "+PACKAGEID+"");
        sleep(2);
        Reporter.log("Remove App " + pkg2 + " on SureLock Homescreen", true);
    }

    public void launchSureLock() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am start -a android.intent.action.MAIN -n "+PACKAGEID+"/.ClearDefaultsActivity");
        sleep(4);
        Reporter.log("Launched SureLock", true);
    }

    public void exitSureLock() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am broadcast -a "+PACKAGEID+".COMMUNICATOR -e \"password\" \"0000\" -e \"command\" \"exit_surelock\" "+PACKAGEID+"");
        sleep(2);
        Reporter.log("SureLock Exited", true);
    }

    public void clearData(String pkg2) throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell pm clear " + pkg2);
        sleep(2);
        Reporter.log("Cleared App Data of " + pkg2, true);
    }

    public void forceStop(String pkg3) throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am force-stop " + pkg3);
        sleep(2);
        Reporter.log("Force Stop App : " + pkg3, true);
    }

    public void lockScreen() throws IOException, InterruptedException{
        ((LocksDevice) driver).lockDevice();
//		Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell input keyevent 26");
//		sleep(2);
        Reporter.log("Locked the screen", true);
        sleep(7);
    }

    public void setPortraitOrientation() throws InterruptedException, IOException {
//		driver.rotate(ScreenOrientation.PORTRAIT);
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put system user_rotation 0");
        Reporter.log("Set Portrait successfull", true);
        sleep(5);
    }

    public void turnOffAutoOrientation() throws InterruptedException, IOException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put system accelerometer_rotation 0");
        Reporter.log("TurnOff Auto Orientation", true);
        sleep(5);
    }

    public void turnOnAutoOrientation() throws InterruptedException, IOException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+"shell settings put system accelerometer_rotation 1");
        Reporter.log("TurnOff Auto Orientation", true);
        sleep(5);
    }

    public String getRotationStatus() throws InterruptedException, IOException {
        Process p = Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings get system accelerometer_rotation");
        BufferedReader output = getOutput(p);
        String rotation = "";
        rotation = output.readLine();
        System.out.println(rotation);
        return rotation;
    }

    public void verifyRotationIsOn() throws IOException, InterruptedException {
        String status = getRotationStatus();
        if (status.contains("1")){
            Reporter.log("PASS >>> Rotation is in ON Status", true);
        }
        else {
            Assert.fail("Rotation is not in On status");
        }
    }

    public void verifyRotationIsOff() throws IOException, InterruptedException {
        String status = getRotationStatus();
        if (status.contains("0")){
            Reporter.log("PASS >>> Rotation is in Off Status", true);
        }
        else {
            Assert.fail("Rotation is not in Off status");
        }
    }

    public void setLandscapeOrientation() throws InterruptedException, IOException {
//		driver.rotate(ScreenOrientation.LANDSCAPE);
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put system user_rotation 1");
        Reporter.log("Set Landscape successfull", true);
        sleep(5);
    }

    public void unlockScreen() throws IOException, InterruptedException{
//		Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell input swipe 200 900 200 300");
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell input keyevent KEYCODE_WAKEUP");
        sleep(2);
        Reporter.log("Unlocked the screen", true);
    }

    public void moveSettingsFileToOtherFolder() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell mv /sdcard/SureLock.settings /sdcard/Download/SureLock.settings");
        sleep(2);
        Reporter.log("Moved Settings file to Other location", true);
    }

    public void moveCustomizedSLSettingsFileToOtherFolder() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell mv /sdcard/SureLock1.settings /sdcard/Download/SureLock1.settings");
        sleep(2);
        Reporter.log("Moved Settings file to Other location", true);
    }

    public void deleteSettingsFileToOtherFolder() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell rm -f /sdcard/Download/SureLock.settings");
        sleep(2);
        Reporter.log("Deleted Settings file to Other location", true);
    }

    public void moveSAMFileToOtherFolder() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell mv /sdcard/SL_SAM_EXIT_.txt /sdcard/Download/SL_SAM_EXIT_.txt");
        sleep(2);
        Reporter.log("Moved SAM file to Other location", true);
    }

    public void enableGPS() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put secure location_providers_allowed gps");
        sleep(2);
        Reporter.log("GPS enabled Successfully", true);
    }

    public void disableGPS() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put secure location_providers_allowed off");
        sleep(2);
        Reporter.log("GPS disabled Successfully", true);
    }

    public void runMonkeyTestingCmd(int intents, int delay) throws IOException, InterruptedException{
        Reporter.log("Executing intents " + intents + " times on SureLock Homescreen", true);
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell monkey -p "+PACKAGEID+" -v "+intents);
        sleep(delay);
    }

    public void rebootDevice() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb reboot");
        sleep(2);
        Reporter.log("Rebooting device", true);
    }

    public void tapOnProceedBtn() throws InterruptedException{
        ProceedBtn.click();
        sleep(2);
        Reporter.log("Tapped on Proceed Button", true);
    }

    public void tapOnAdminActivateSL() throws InterruptedException{
        waitForidPresent("com.android.settings:id/admin_icon", 20);
        sleep(4);
        try {
            scrollTo("Cancel");
            AdminActivateSL.click();
            Reporter.log("Tapped on Device Admin Activate SL", true);
        } catch (Exception e) {
            scrollTo("CANCEL");
            AdminActivateSL.click();
            Reporter.log("Tapped on Device Admin Activate SL", true);
        }
        sleep(2);
    }

    public void tapOnAdminActivateNix() throws InterruptedException{
        waitForidPresent("com.android.settings:id/action_button", 20);
        sleep(4);
        try {
            scrollTo("Cancel");
            AdminActivateSL.click();
            Reporter.log("Tapped on Device Admin Activate Nix", true);
        } catch (Exception e) {
            scrollTo("CANCEL");
            AdminActivateSL.click();
            Reporter.log("Tapped on Device Admin Activate Nix", true);
        }
        sleep(2);
    }

    public void tapOnKLMSAgent() {
        KLMSAgent.click();
        Reporter.log("Tapped on KLMS Agent checkbox", true);
    }

    public void takeScreenShot() {
        destDir = "screenshots";			// Set folder name to store screenshots.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);	// Capture screenshot.
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");	// Set date format to set It as screenshot file name.
        new File(destDir).mkdirs();						// Create folder under project with name "screenshots" provided to destDir.
        String destFile = dateFormat.format(new Date()) + ".png";	// Set file name using current date time.
        try {
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));  // Copy paste file at destination folder location
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FindBy(id="uName")
    private WebElement userNAmeEdt;

    @FindBy(id="pass")
    private WebElement passwordEdt;

    @FindBy(id="loginBtn")
    private WebElement loginBtn;

    public static WebDriver getBrowser() throws IOException{
        System.setProperty("webdriver.chrome.driver", "D:\\softwares\\ChromeDriver\\chromedriver.exe");
        chromedriver = new ChromeDriver();
        return chromedriver;
    }

    public void waitForXpathPresentChrome(String wbXpath){
        WebDriverWait wait = new WebDriverWait(chromedriver, 50);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(wbXpath)));
    }

    public void waitForNamePresentChrome(String wbName){
        WebDriverWait wait = new WebDriverWait(chromedriver,100);
        wait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.name(wbName)));
    }

    public void waitForidPresentChrome(String wbid){
        WebDriverWait wait = new WebDriverWait(chromedriver,100);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(wbid)));
    }

    public void waitForVisibilityOfChrome(String wbxpath){
        WebDriverWait wait = new WebDriverWait(chromedriver,100);
        wait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.xpath(wbxpath)));
    }

    public void waitForidClickableChrome(String wbid){
        WebDriverWait wait = new WebDriverWait(chromedriver,100);
        wait.until(ExpectedConditions.elementToBeClickable
                (By.id(wbid)));
    }

    public void SwipeUp() throws IOException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell input swipe 500 1000 300 300");
        System.out.println("Swipe up done");
    }



    public void verifyFileIsInLocalStorage(String appName)
    {
        try {
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(" + appName + ").instance(0))")).isDisplayed();
            Reporter.log("PASS >>> Applied file is present in the Local storage", true);
        }
        catch(Exception e){
            Assert.fail("Applied file is not present in the Local storage");
        }
    }

    public void activateSureFox() throws IOException, InterruptedException{
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am broadcast -a com.gears42.surefox.COMMUNICATOR -e \"command\" \"activate\" -e \"password\" \"0000\" -e \"activation_code\" \"567446\" -e \"prefered_activation_id\" \"0\" com.gears42.surefox");
        sleep(10);
        Reporter.log("Activated SureLock", true);
    }

    public void goToSFoxAdminSettings() throws IOException, InterruptedException{
        sleep(2);
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell am broadcast -a com.gears42.surefox.COMMUNICATOR -e \"command\" \"open_admin_settings\" -e \"password\" \"0000\" com.gears42.surefox");
        Reporter.log("Navigated to Admin Settings", true);
        sleep(10);
    }

    public void removeAppFromDevice(String bundledId) {
        try {
            Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" uninstall "+bundledId+"");
            System.out.println(""+bundledId+": App is removed");
        }
        catch(Exception e) {
            Reporter.log("App is already deleted", true);
        }
    }

    public void deviceSleepTimeInSec(int sleep) throws IOException, InterruptedException{
        int temp = sleep*1000;
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put system screen_off_timeout "+temp+"");
        sleep(2);
        Reporter.log("Device sleep is set to "+sleep+" seconds", true);
    }


    public static String Mainwindow1;
    public static ArrayList<String> tabs;

    public void OpenNewTabAndEnterURL(String url) throws InterruptedException, AWTException {
        JavascriptExecutor jse = (JavascriptExecutor)chromedriver;
        jse.executeScript("window.open()");
        chromedriver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
        tabs = new ArrayList<String> (chromedriver.getWindowHandles());
        chromedriver.switchTo().window(tabs.get(1));
        System.out.println(tabs);
        chromedriver.get(url);
        sleep(5);
    }
    public void SwitchToChildWindow() throws InterruptedException {
        ArrayList<String> windowHandle =new ArrayList<String> (chromedriver.getWindowHandles());
        chromedriver.switchTo().window(windowHandle.get(1));
        Reporter.log("Switched to child window", true);
        sleep(5);
    }

    public void setSleepTimeTo10Minutes() throws IOException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell settings put system screen_off_timeout 3600000");
        Reporter.log("Set sleep Time to 10minutes", true);
    }

    public void clickOnDynamicrefreshbutton() throws InterruptedException {
        WebElement DynamicLoading = chromedriver.findElement(By.xpath("//div[@id='refreshButton']"));
        DynamicLoading.click();
        try {
            while(chromedriver.findElement(By.xpath("//div[@class='lds-ring']")).isDisplayed())
            {
                sleep(2);
                System.out.println("Loading");
            }
        }
        catch(Exception e) {
            while(DynamicLoading.isDisplayed())
            {
                sleep(2);
                System.out.println("Loading");
            }
        }
        Reporter.log("Clicked on dynamic refreshbutton", true);
        sleep(5);
    }


    public void RemoveNixSettingsAndRemoveNixApp() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell rm /sdcard/NixSettings.xml");
        sleep(3);
        Runtime.getRuntime().exec("adb uninstall com.nix");
        sleep(7);
        Reporter.log("Nix settings and Nix App are removed", true);
    }

    public void tapOnOverwriteButton() {
        try {
            OverwriteBtn.click();
            Reporter.log("Tapped on Overwrite button", true);
        }
        catch(Exception e) {
            System.out.println("Overwrite button is not displayed");
        }
    }



    @FindBy(xpath="//android.widget.TextView[@text='Open with']")
    private WebElement OpenWith;

    public void tapOnRequiredAppInOpenWith(String option) throws InterruptedException{
        try {
            try {
                OpenWith.isDisplayed();
                driver.findElementByXPath("//android.widget.TextView[@text='"+option+"']").click();
                Reporter.log("Tapped on "+option+"", true);
                tapOnAlwaysbtn();
            }
            catch (Exception e1) {
                Reporter.log("Always button is not required", true);
            }
        }
        catch (Exception e) {
            Reporter.log("Open with prompt is not displayed", true);
        }
    }

    public void tapOnBackButtonTillAdminSettings() throws InterruptedException, IOException {
        try {
            adminSettings.isDisplayed();
            Reporter.log("Admin Settings is displayed", true);
        } catch (Exception e) {
            goBack();
        }
    }

    public void tapOnDoneButtonTillAdminSettings() throws InterruptedException, IOException {
        try {
            unlockDevice();
            unlockScreen();
            adminSettings.isDisplayed();
            Reporter.log("Admin Settings is displayed", true);
        } catch (Exception e) {
            unlockDevice();
            unlockScreen();
            tapOnDoneBtn();
        }
    }

    public void tapOnBackButtonTillAdminSettings3Times() throws InterruptedException, IOException {
        tapOnBackButtonTillAdminSettings();
        tapOnBackButtonTillAdminSettings();
        tapOnBackButtonTillAdminSettings();
        sleep(2);
    }

    public void tapOnDoneButtonTillAdminSettings3Times() throws InterruptedException, IOException {
        tapOnDoneButtonTillAdminSettings();
        tapOnDoneButtonTillAdminSettings();
        tapOnDoneButtonTillAdminSettings();
    }

    public void longPress(WebElement element) {
        new TouchAction(driver).longPress(longPressOptions().withElement((ElementOption)element).withDuration(Duration.ofMillis(500))).release().perform();
        Reporter.log("Long press done", true);
    }

    public void LongPressOnText(String text) throws InterruptedException {
        WebElement Element = driver.findElementByXPath("//android.widget.TextView[@text='"+text+"']");
        TouchAction action = new TouchAction(driver).longPress(longPressOptions().withElement(element(Element)).withDuration(Duration.ofMillis(2000))).release().perform();
//		action.longPress(longPressOptions().withElement(element(Element)).withDuration(ofSeconds(2))).release().perform();
        Reporter.log("Long pressed on "+text,true);
    }

    public void openNotificationPanel() throws IOException, InterruptedException {
        sleep(5);
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell service call statusbar 1");
        Reporter.log("Scroll down to Notification Panel", true);
    }

    public void swipeDownFromStatusBar() throws InterruptedException {
        sleep(5);
        new TouchAction(driver).press(PointOption.point(30, 0)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(500,500)).release().perform();
        Reporter.log("Scroll down to Notification Panel", true);
    }

    public void openQuickSettings() throws IOException, InterruptedException {
        sleep(5);
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell cmd statusbar expand-settings");
        Reporter.log("Open Quick settings", true);
    }

    public void collapseQuickSettings() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell cmd statusbar collapse");
        Reporter.log("Collapse Quick settings", true);
        sleep(3);
    }

    public void closeNotificationPanel() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell service call statusbar 2");
        Reporter.log("Notification Panel is Closed", true);
    }

    public void uiServerClear() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb uninstall io.appium.uiautomator2.server");
        sleep(2);
        Runtime.getRuntime().exec("adb uninstall io.appium.uiautomator2.server.test");
        sleep(2);
        Runtime.getRuntime().exec("adb devices");
    }

    public void verifySamsungPassIsDisplayedAndClose() {
        try {
            SamsungPassclose.isDisplayed();
            SamsungPassclose.click();
            System.out.println("Tapped on Samsung Pass close button");
        } catch (Exception e) {
            System.out.println("Samsung Pass is not displayed");
        }
    }

    public static String os;
    public static void osVersion() throws IOException {
        os=getOSversion();
    }

    public static String getOSversion() throws IOException {
        Process p = Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell getprop ro.build.version.release");
        BufferedReader output = getOutput(p);
        String ligne = "";
        ligne = output.readLine();
        return ligne;
    }



    public String getPID(String packageId) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell pidof "+packageId+"");
        BufferedReader output = getOutput(p);
        String PID = "";
        PID = output.readLine();
        System.out.println(PID);
        return PID;
    }



    public void deleteFileFromDevice(String Path, String FileName) throws IOException {
        Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell rm -f "+Path+FileName+"");
    }

    public void quitSession() throws InterruptedException {
        driver.quit();
        sleep(10);
    }

    public void getDataUsage() throws Exception {
        ((HasSupportedPerformanceDataType) driver).getPerformanceData("com.android.vending", "networkinfo", 10);
    }

    private HashMap<String, Integer> getMemoryInfo(AppiumDriver driver) throws Exception {
        List<List<Object>> data = ((HasSupportedPerformanceDataType) driver).getPerformanceData("io.appium.android.apis", "networkinfo", 10);
        HashMap<String, Integer> readableData = new HashMap<String, Integer>();
        for (int i = 0; i < data.get(0).size(); i++) {
            int val;
            if (data.get(1).get(i) == null) {
                val = 0;
            } else {
                val = Integer.parseInt((String) data.get(1).get(i));
            }
            readableData.put((String) data.get(0).get(i), val);
        }
        return readableData;
    }

    public void getDataUsage21() throws Exception {
        HashMap<String, Integer> memoryInfo = getMemoryInfo(driver);
        int setSize = memoryInfo.get("totalPss");
        System.out.println(setSize);
    }


    public void verifyAndActivateSureLock() throws IOException, InterruptedException {
        osVersion();
        if (os.contains("5")) {
            activateSureLock();
        } else {
            System.out.println("Above lollipop");
        }
    }

    public void tapOnTheFeature(String Feature) throws InterruptedException {
        sleep(1);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + Feature + "\").instance(0))")).click();
        Reporter.log("Tapped on "+Feature);
        sleep(2);
    }

    public void tapToEnableTheFeature(String ElementText)throws InterruptedException{
        sleep(2);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + ElementText + "\").instance(0))"));
        if(driver.findElementByXPath("//android.widget.TextView[@text='"+ElementText+"']/../../android.widget.LinearLayout/android.widget.CheckBox").getAttribute("checked").contains("true")){
            Reporter.log(ElementText+" Is Already Enabled",true);
        }else {
            driver.findElementByXPath("//android.widget.TextView[@text='"+ElementText+"']/../../android.widget.LinearLayout/android.widget.CheckBox").click();
        }
    }

    public void tapToDisableTheFeature(String ElementText)throws InterruptedException{
        sleep(1);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + ElementText + "\").instance(0))"));
        if(driver.findElementByXPath("//android.widget.TextView[@text='"+ElementText+"']/../../android.widget.LinearLayout/android.widget.CheckBox").getAttribute("checked").contains("false")){
            Reporter.log(ElementText+" Is Already Disabled",true);
        }else {
            driver.findElementByXPath("//android.widget.TextView[@text='"+ElementText+"']/../../android.widget.LinearLayout/android.widget.CheckBox").click();
            Reporter.log(ElementText+" Is  Disabled",true);
        }
        sleep(1);
    }

    public void verifyCheckBoxIsDisabledOf(String ElementText)throws InterruptedException{
        sleep(1);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + ElementText + "\").instance(0))"));
        if(driver.findElementByXPath("//android.widget.TextView[@text='"+ElementText+"']/../../android.widget.LinearLayout/android.widget.CheckBox").getAttribute("checked").contains("false")){
            Reporter.log("PASS >>> Check Box of "+ElementText+" Is Disabled",true);
        }else {
            Reporter.log("FAIL >>> Check Box of "+ElementText+" Is Enabled",true);
            Assert.fail("FAIL >>> Check Box of "+ElementText+" Is Enabled");
        }
        sleep(1);
    }

    public void verifyCheckBoxIsEnabledOf(String ElementText)throws InterruptedException{
        sleep(1);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + ElementText + "\").instance(0))"));
        if(driver.findElementByXPath("//android.widget.TextView[@text='"+ElementText+"']/../../android.widget.LinearLayout/android.widget.CheckBox").getAttribute("checked").contains("true")){
            Reporter.log("PASS >>> Check Box of "+ElementText+" Is Enabled",true);
        }else {
            Reporter.log("FAIL >>> Check Box of "+ElementText+" Is Disabled",true);
            Assert.fail("FAIL >>> Check Box of "+ElementText+" Is Disabled");
        }
        sleep(1);
    }

    public void verifyOSVersionIs10AndAbove() throws IOException {
        os=getOSversion();
        int index = os.indexOf(".");
        if(index==1) {
            os = os.substring(0, Math.min(os.length(), index+2));
        }else {
            os = os.substring(0, Math.min(os.length(), index+2));
        }
        double version = Double.parseDouble(os);
        if(version >= 10)
            Reporter.log("Device Os is "+version, true);
        else
            throw new SkipException("Device OS is lesser than provided, - Test is skipped");
    }

    public static void verifyOSVersionIsGreaterThanProvided(double ver) throws IOException {
        os=getOSversion();
        int index = os.indexOf(".");
        if(index==1) {
            os = os.substring(0, Math.min(os.length(), index+2));
        }else {
            os = os.substring(0, Math.min(os.length(), index+2));
        }
        double version = Double.parseDouble(os);
        if(version >= ver)
            Reporter.log("PASS >>> Device Os Version is "+version, true);
        else
            throw new SkipException("Device OS is lesser than provided, - Test is skipped");
    }

    public void isGrayedOut(String Feature) {
        if(driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + Feature + "\").instance(0))")).isEnabled()) {
            System.out.println("FAIL >>> "+Feature+" is Not Grayed Out");
            Assert.fail(Feature+" is Not Grayed Out");}
        else {
            System.out.println("PASS >>> "+Feature+" is Grayed Out");
        }
    }

    public void verifyFeatureIsDisplayed(String Feature) throws InterruptedException {
        sleep(1);
        try{
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + Feature + "\").instance(0))")).isDisplayed();
            Reporter.log(Feature+" Feature is Displayed", true);
        }catch(Exception e) {
            Assert.fail(Feature+" Feature is not Displayed");
        }
    }

    public void UnlockingDeviceThroughPassword(String pwd) throws IOException, InterruptedException
    {
        Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_WAKEUP");
        sleep(3);
        Runtime.getRuntime().exec("adb shell input keyevent 82");
        sleep(3);
        Runtime.getRuntime().exec("adb shell input text "+pwd);
        sleep(2);
        Runtime.getRuntime().exec("adb shell input keyevent 66");
    }


    public void verifyPowerOff() {
        try{
            driver.findElementByXPath("//android.widget.TextView[@text = 'Power off']").isDisplayed();
            Reporter.log("PASS >>> Power Off is Displayed", true);
        }catch(Exception e) {
            Assert.fail("FAIL >>> POwer Off is not Displayed");
        }
    }

    public void verifyDeviceDoesNotSupportsKnox() throws IOException {
        Process p =	Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+" shell getprop ro.config.knox");
        BufferedReader output = getOutput(p);
        String ligne = "";
        ligne = output.readLine();
        if(ligne==null)
            Reporter.log("PASS >>> Device does not supports Knox", true);
        else
            throw new SkipException("Device does support Knox, - Test is skipped");
    }

    public void verifyEAIsNotInstalledOnDevice() {
        try{
            driver.isAppInstalled("com.gears42.enterpriseagent");
            throw new SkipException("EA is installed in the device, - Test is skipped");
        }catch(Exception e) {
            Reporter.log("PASS >>> EA is not installed", true);
        }
    }

    @FindBy(xpath="//android.widget.Switch[@index='0']") //android.widget.TextView[@text='Allow usage tracking']
    private WebElement switchButton;

    public void tapOnSwitchButton() throws InterruptedException{
        switchButton.click();
        Reporter.log("Tapped on Allow Usage Access", true);
        sleep(2);
    }

    public void verifyForegroundApp(String packageName) throws IOException {
        Process p =	Runtime.getRuntime().exec("adb "+ASTROFARMCOMM+"shell \"dumpsys window windows | grep -E 'mCurrentFocus' | cut -d '/' -f1 | sed 's/.* //g'\"");
        BufferedReader output = getOutput(p);
        String ligne = "";
        ligne = output.readLine();
        if(ligne.equals(packageName)) {
            Reporter.log("PASS >>> "+ligne+" Is on foreground", true);
        }else {
            Assert.fail("FAIL >>> "+ligne+"Is on foreground");
        }
    }


}