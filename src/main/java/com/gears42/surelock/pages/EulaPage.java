package com.gears42.surelock.pages;

import com.gears42.surelock.properties.TestProperties;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class EulaPage extends BasePage {


    @FindBy(xpath = "//android.widget.Button[@text='ACCEPT' or @text='Accept']")
    private WebElement acceptBtn;

    @FindBy(xpath = "//android.widget.Button[@text='Continue'or @text='CONTINUE']")
    private WebElement continueBtn;

    @FindBy(id = "com.gears42.surelock:id/button_continue_done")
    private WebElement continueBtn2;

    @FindBy(xpath = "//android.widget.Button[@text='ALLOW' or @text='Allow']")
    private WebElement AllowBtn;

    @FindBy(xpath = "//android.widget.TextView[@text='Configure Runtime Permissions']")
    private WebElement configRuntimePermissions;

    @FindBy(id = "com.gears42.surelock:id/chb_permission")
    private List<WebElement> runTimePermission;


    public EulaPage(AppiumDriver<WebElement> driver) throws InterruptedException {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(TestProperties.INSTANCE.IMP_WAIT, TimeUnit.SECONDS);
    }

    public void configRuntimePermissions() throws InterruptedException {
        try {
            configRuntimePermissions.click();
            tapOnRunTimePermissions();
            continueBtn.click();
            tapOnAllowButton();
            continueBtn.click();
            Reporter.log("Enabled all Runtime Permissions Successfully", true);
        } catch (Exception e) {
            Reporter.log("Enabled all Runtime Permissions Failed", true);
        }
    }

    public void tapOnAcceptEula() throws InterruptedException {
        try {
            AllowBtn.click();
            acceptBtn.click();
            continueBtn2.click();
            Reporter.log("EULA Accepted Successfully", true);
            sleep(2);
            continueBtn.click();
        } catch (Exception e) {
            try {
                acceptBtn.click();
            } catch (Exception ex) {
                Reporter.log("EULA Accepted Failed", true);
            }
        }
    }


    public void tapOnRunTimePermissions() throws InterruptedException {
        int RunTimePermissionCount = runTimePermission.size();
        if (RunTimePermissionCount == 6) {
            for (int i = 0; i < runTimePermission.size(); i++) {
                runTimePermission.get(i).click();
            }
        } else {
            Assert.fail("Mismatch in Run Time Permissions");
        }
    }

    public void tapOnAllowButton() throws InterruptedException {
        try {
            while (AllowBtn.isDisplayed()) {
                AllowBtn.click();
            }
        } catch (Exception e) {
            Reporter.log("Tapped on all allow button", true);
        }
    }

}
