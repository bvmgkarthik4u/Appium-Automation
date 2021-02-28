package com.gears42.surelock.detailTestCases;

import com.gears42.surelock.base.BaseTestInitialization;
import com.gears42.surelock.pages.EulaPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SLAllowedApps extends BaseTestInitialization {
    @Test(priority = 1, description = "Accept EULA")
    public void acceptEULA() throws InterruptedException {
        EulaPage lEulaPage = new EulaPage(mAppiumDriver);
        lEulaPage.tapOnAcceptEula();
        Assert.assertTrue(true, "EULA accepted Successfully");
    }

    @Test(priority = 2, description = "Config Runtime Permissions")
    public void configRuntimePermissions() throws InterruptedException {
        EulaPage lEulaPage = new EulaPage(mAppiumDriver);
        lEulaPage.configRuntimePermissions();
        Assert.assertTrue(true, "Enabled all Runtime Permissions Successfully");
    }

}
