package com.gears42.surelock.detailTestCases;

import com.gears42.surelock.base.BaseTestInitialization;

import java.awt.AWTException;
import java.io.IOException;

import com.gears42.surelock.pages.EulaPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;


import static java.lang.Thread.sleep;

public class SLAllowedApps extends BaseTestInitialization
{
	@Test(priority=1, description="Accept EULA")
	public void acceptEULA() throws InterruptedException, IOException {
		EulaPage lEulaPage = new EulaPage(mAppiumDriver);
		lEulaPage.tapOnAcceptEula();
		Reporter.log("SL Home screen displayed", true);
		Assert.assertTrue(true, "EULA accepted Successfully");
	} 

}
