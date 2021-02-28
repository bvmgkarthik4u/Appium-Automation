package com.gears42.surelock.base;

import com.gears42.surelock.properties.TestProperties;
import com.gears42.surelock.properties.propertiesModel.DriverInitialisation;
import com.gears42.surelock.properties.propertiesModel.Properties;
import com.gears42.surelock.util.AppiumDriverEventListener;
import com.google.gson.Gson;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTestInitialization {

	public static AppiumDriver<WebElement> mAppiumDriver;
	public DriverInitialisation mDriverInitialisation;
	
	@BeforeClass
	public void preCondition() throws IOException, InterruptedException, MalformedURLException
	{
		mDriverInitialisation = TestProperties.INSTANCE.mProperties.getDriverInitialisation();
		DesiredCapabilities lCapabilities =new DesiredCapabilities();
		lCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, mDriverInitialisation.getDeviceName());
		lCapabilities.setCapability("appPackage", mDriverInitialisation.getAppPackage());
		lCapabilities.setCapability("appActivity", mDriverInitialisation.getAppActivity());
//		lCapabilities.setCapability("deviceId", "192.168.1.4:5555");
		lCapabilities.setCapability("noReset",mDriverInitialisation.getNoReset());
		lCapabilities.setCapability("adbExecTimeout",mDriverInitialisation.getAdbExecTimeout());
		System.out.println("No Reset happened for SureLock");
		lCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,mDriverInitialisation.getNewCommandTimeout());
		System.out.println("SureLock is about to launch");
		
		try{
			AndroidDriver<WebElement> lAppiumDriver = new AndroidDriver<WebElement>(new URL(mDriverInitialisation.getServerPath()), lCapabilities);
			mAppiumDriver = EventFiringWebDriverFactory.getEventFiringWebDriver(lAppiumDriver, new AppiumDriverEventListener());
		}
		catch(Throwable e){
			System.out.println(e);
		}

	}



}

