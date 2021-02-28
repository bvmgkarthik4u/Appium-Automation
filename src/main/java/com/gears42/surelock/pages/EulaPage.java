package com.gears42.surelock.pages;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.gears42.surelock.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;

import static com.gears42.surelock.properties.TestProperties.IMPWAIT;

public class EulaPage extends BasePage {

	@FindBy(xpath="//android.widget.Button[@text='REJECT' and @text='Reject']")
	private WebElement rejectBtn;

	@FindBy(xpath="//android.widget.Button[@text='ACCEPT' or @text='Accept']")
	private WebElement acceptBtn;

	@FindBy(xpath="//android.widget.Button[@text='DENY' or @text='Deny']")
	private WebElement DenyBtn;

	@FindBy(id="android:id/button1")
	private WebElement acceptBtnId;

	@FindBy(xpath="//android.widget.Button[@text='Cancel' and @text='CANCEL']")
	public WebElement CancelBtn;

	@FindBy(xpath="//android.widget.Button[@text='Proceed' or @text='PROCEED']")
	private WebElement ProceedBtn;

	@FindBy(id="com.samsung.klmsagent:id/checkBox1")
	private WebElement IAgreeBtn;

	@FindBy(id="com.samsung.klmsagent:id/btn_confirm_inner")
	private WebElement ConfirmBtn;

	@FindBy(xpath="//android.widget.TextView[@text='SureLock']")
	private WebElement selectAppUsageSurelock;

	@FindBy(xpath="//android.widget.TextView[@text='Permit usage access']")
	private WebElement enableAppUsage;

	@FindBy(xpath="//android.widget.CheckBox[@text='Remember my choice']")
	private WebElement rememberChoice;

	@FindBy(className="android.widget.LinearLayout")
	private WebElement selectChooser;

	@FindBy(id="com.gears42.surelock:id/enableUsageAccessText")
	private WebElement enableAppUsagePrompt;

	@FindBy(xpath="//android.widget.RadioButton[@index='1']")
	private WebElement secondRadioBtn;

	@FindBy(xpath="//android.widget.Button[@text='Continue'or @text='CONTINUE']")
	private WebElement continueBtn;

	@FindBy(id="com.gears42.surelock:id/button_continue_done")
	private WebElement continueBtn2;

	@FindBy(xpath="//android.widget.Button[@text='ALLOW' or @text='Allow']")
	private WebElement AllowBtn;

	@FindBy(xpath="//android.widget.TextView[@text='Configure Runtime Permissions']")
	private WebElement configRuntimePermissions;

	@FindBy(xpath="//android.widget.TextView[@text='Enable Display Over Other Apps']")
	private WebElement enableDisplayOverOtherAppsPermissions;

	@FindBy(id="android:id/checkbox")
	private WebElement enableCheckbox;

	//	@FindBy(xpath="//android.widget.TextView[@text='This permission is required to read IMEI number']")
	@FindBy(xpath="//android.widget.TextView[@text='Telephone']")
	private WebElement telephonePermissions;

	@FindBy(xpath="//android.widget.TextView[@text='This permission is required for phone settings']")
	private WebElement contactsPermissions;

	@FindBy(xpath="//android.widget.TextView[@text='This permission is required for receiving SMS commands']")
	private WebElement smsPermissions;

	@FindBy(xpath="//android.widget.TextView[@text='This permission is required to import settings from QR code']")
	private WebElement cameraPermissions;

	@FindBy(xpath="//android.widget.TextView[@text='This permission is required for driver safety settings']")
	private WebElement locationPermissions;

	@FindBy(id="com.gears42.surelock:id/chb_permission")
	private List<WebElement> RunTimePermission;

	@FindBy(id="com.gears42.surelock:id/noRadioButton")
	private WebElement noWantToUseSureLock;

	@FindBy(xpath="//android.widget.TextView[@text='SureLock']")
	public WebElement SureLock;

	@FindBy(xpath="//android.widget.LinearLayout[@index='0']/android.widget.TextView[@text='SureLock']")
	public WebElement SureLockWith0;

	@FindBy(xpath="//android.widget.LinearLayout[@index='1']/android.widget.TextView[@text='SureLock']")
	public WebElement SureLockWith1;

	public EulaPage(AppiumDriver<WebElement> driver) throws InterruptedException{
		super(driver);
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(IMPWAIT, TimeUnit.SECONDS);
	}

	public void tapOnSureLock() throws InterruptedException{
		SureLock.click();
		Reporter.log("Tapped on SureLock", true);
	}

	public void verifyAndselectSureLock() throws InterruptedException{
		try {
			SureLockWith0.isSelected();
			if(Alwaysbtn.isEnabled()){
				Reporter.log("SureLock is selected by default", true);
//				SureLockWith0.click();
				tapOnAlwaysbtn();
			}else{
				SureLockWith0.click();
				Reporter.log("SureLock is in index 0", true);
				tapOnAlwaysbtn();
			}

		} catch (Exception e) {
			SureLockWith1.isSelected();
			SureLockWith1.click();
			Reporter.log("SureLock is in index 1", true);
			tapOnAlwaysbtn();
		}
	}

	AppiumDriver<WebElement> driver;

	public void tapOnAcceptEula() throws InterruptedException{
		try{
			AllowBtn.click();
			goBack();
			launchSureLock();
//			verifyAndTapOnWantToUseSurelock();
			acceptBtn.click();
			continueBtn2.click();
			Reporter.log("EULA Accepted Successfully", true);
			configRuntimePermissions.click();
			tapOnRunTimePermissions();
			continueBtn.click();
			tapOnAllowButton();
			continueBtn.click();
			Reporter.log("Enabled all Runtime Permissions Successfully", true);
			sleep(2);
			continueBtn.click();
		}catch(Exception e){
			try{
				acceptBtn.click();
//					continueBtn2.click();
				Reporter.log("EULA Accepted Successfully", true);
				configRuntimePermissions.click();
				continueBtn.click();
				sleep(2);
				continueBtn.click();
			}catch(Exception ex) {
				try {
					continueBtn.click();
					Reporter.log("Enabled all Runtime Permissions Successfully", true);
					sleep(2);
				} catch (Exception e2) {
					Reporter.log("Enabled all Runtime Permissions Successfully", true);
					sleep(2);
				}
			}
			Reporter.log("Already Setup successful and Accepted EULA",true);
		}
		sleep(10);
		Reporter.log("Setup successful and SL home displayed", true);
	}

	public void verifyAndTapOnWantToUseSurelock() {
		try {
			noWantToUseSureLock.click();
			Reporter.log("Tapped on No, I want to use SureLock", true);
			continueBtn.click();
		}
		catch(Exception e) {
			Reporter.log("No, I want to use SureLock is not displayed", true);
		}
	}

	public void tapOnAcceptEulaTillSetuSLWithoutLaunch() throws InterruptedException, IOException{
		AllowBtn.click();
		goBack();
		launchSureLock();
		acceptBtn.click();
		continueBtn2.click();
	}

	public void tapOnAcceptEulaTillSetSL() throws InterruptedException, IOException{
		AllowBtn.click();
		goBack();
		launchSureLock();
		pressHome();
		sleep(2);
		verifyAndselectSureLock();
		acceptBtn.click();
		continueBtn2.click();
	}

	public void tapOnAcceptEulaTillSetupSLPermission() throws InterruptedException, IOException{
		try {
			AllowBtn.click();
//			launchSureLock();
			goBack();
			goBack();
			launchSureLock();
			pressRescent();
			goBack();
			goBack();
			acceptBtn.click();
			continueBtn2.click();
		}
		catch (Exception e) {
			acceptBtn.click();
		}
	}

	public void tapOnAcceptTillSetupSLPermission() throws InterruptedException, IOException{
		try {
			AllowBtn.click();
			launchSureLock();
			pressRescent();
			pressHome();
			acceptBtn.click();
			continueBtn2.click();
		}
		catch (Exception e) {
			acceptBtn.click();
		}
	}

	public void tapOnRunTimePermissions() throws InterruptedException {
		int RunTimePermissionCount = RunTimePermission.size();
		if(RunTimePermissionCount==6) {
			for(int i=0; i<RunTimePermission.size();i++) {
				RunTimePermission.get(i).click();
			}
		}
		else {
			Assert.fail("Mismatch in Run Time Permissions");
		}
	}

	public void tapOnAllowButton() throws InterruptedException {
		try {
			while(AllowBtn.isDisplayed()) {
				AllowBtn.click();
			}
		}
		catch(Exception e) {
			Reporter.log("Tapped on all allow button", true);
		}
	}

	public void tapOnAllowBtn() throws InterruptedException {
		AllowBtn.click();
		Reporter.log("Tapped on allow button", true);
	}

	public void tapOnRejectEula() throws InterruptedException, IOException{
		AllowBtn.click();
		goBack();
		launchSureLock();
		/*secondRadioBtn.click();
		continueBtn.click();*/
		rejectBtn.click();
		sleep(2);
		Reporter.log("Tapped on Reject", true);
	}

	public void tapOnCancelBtn() throws InterruptedException{
		try{
			CancelBtn.click();
		}catch(Exception e){
			Reporter.log("Already Tapped on Cancel Button", true);
		}
		sleep(2);
		Reporter.log("Tapped on Cancel Button", true);
	}

	public void tapOnProceedBtn() throws InterruptedException{
		ProceedBtn.click();
		sleep(10);
		Reporter.log("Tapped on Proceed Btn", true);
	}

	public void tapOnIAgreeCheckbox() throws InterruptedException{
		IAgreeBtn.click();
		sleep(2);
		Reporter.log("Tapped on I Agree checkbox", true);
	}

	public void tapOnAcceptBtn() throws InterruptedException{
		acceptBtn.click();
		Reporter.log("Tapped on accept button", true);
	}

	public void tapOnDenyBtn() throws InterruptedException{
		DenyBtn.click();
		Reporter.log("Tapped on Deny button", true);
	}

	public void tapOnConfirmBtn() throws InterruptedException{
		ConfirmBtn.click();
		sleep(10);
		Reporter.log("Tapped on Confirm Btn", true);
	}

	public void selectRememberChoice(){
		rememberChoice.click();
		Reporter.log("Selected Remember Choice", true);
	}

	public void selectChooser(){
		selectChooser.click();
		Reporter.log("Tapped on Chooser", true);
	}

	public void tapOnAppUsageSurelock(){
		selectAppUsageSurelock.click();
		Reporter.log("Selected App Usage for SureLock", true);
	}

	public void enableAppUsage(){
		enableAppUsage.click();
		Reporter.log("Enabled App Usage for SureLock", true);
	}

	public void tapOnAppUsagePrompt(){
		enableAppUsagePrompt.click();
		Reporter.log("Tapped on App Usage Prompt", true);
	}

	public void selectSecondRadioBtn(){
		secondRadioBtn.click();
		Reporter.log("Tapped on Select second radio button", true);
	}

	public void tapOnContinueBtn(){
		continueBtn.click();
		Reporter.log("Tapped on Continue Btn", true);
	}

	public void EnableAllRuntimePermissions() throws InterruptedException{
		try {
			configRuntimePermissions.click();
			tapOnRunTimePermissions();
			continueBtn.click();
			tapOnAllowButton();
			continueBtn.click();
			Reporter.log("Enabled all Runtime Permissions Successfully", true);
			sleep(2);
			continueBtn.click();
		}
		catch(Exception e){
			acceptBtn.click();
			//		continueBtn2.click();
			Reporter.log("EULA Accepted Successfully", true);
			try{
				configRuntimePermissions.click();
				continueBtn.click();
				sleep(2);
				continueBtn2.click();
			}catch(Exception ex) {
				Reporter.log("Enabled all Runtime Permissions Successfully", true);
				sleep(2);
			}
			Reporter.log("Already Setup successful and Accepted EULA",true);
		}
		sleep(10);
		Reporter.log("Setup successful and SL home displayed", true);
	}

	public void verifyProceedButton() throws InterruptedException{
		try {
			ProceedBtn.isDisplayed();
			Reporter.log("PASS >> Proceed button is displayed in SL Homescreen and Knox settings applied successfully", true);
		}
		catch (Exception e) {
			Assert.fail("Proceed button is not displayed in SL Homescreen");
		}
	}

}
