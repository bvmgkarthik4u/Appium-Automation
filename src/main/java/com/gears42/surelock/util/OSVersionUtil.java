package com.gears42.surelock.util;

import com.gears42.surelock.base.BaseTestInitialization;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OSVersionUtil {
    public static final double ANDROID_10 = 10;
    public static final double PIE = 9.0;
    public static final double OREO = 8.1;
    public static final double NOUGAT = 7.1;
    public static final double MARSHMELLO = 6.0;
    public static final double LOLLIPOP = 5.1;
    public static final double KITKAT = 4.4;
    public static final double ANDROID_11_API_LEVEL = 30;
    public static final double ANDROID_10_API_LEVEL = 29;
    public static final double PIE_API_LEVEL = 28;
    public static final double OREO_MR1_API_LEVEL = 27;
    public static final double OREO_API_LEVEL = 26;
    public static final double NOUGAT_MR1_API_LEVEL = 25;
    public static final double NOUGAT_API_LEVEL = 24;
    public static final double MARSHMELLO_API_LEVEL = 23;
    public static final double LOLLIPOP_MR1_API_LEVEL = 22;
    public static final double LOLLIPOP_API_LEVEL = 21;
    public static final double KITKAT_MAR1_API_LEVEL = 20;
    public static final double KITKAT_API_LEVEL = 19;
    public static final boolean IS_ANDROID_10_ONWORDS = getAPILevel() >= ANDROID_10;
    public static final boolean IS_NOUGAT_BELOW = getAPILevel() < NOUGAT_MR1_API_LEVEL;
    public static final boolean IS_NOUGAT_AND_ABOVE = getAPILevel() >= NOUGAT_MR1_API_LEVEL;
    public static final boolean IS_MARSHMELLO_AND_BELOW = getAPILevel() <= MARSHMELLO_API_LEVEL;

    private static double getVersion() {
        String lStrVersion = (String) BaseTestInitialization.mAppiumDriver.getCapabilities().getCapability("platformVersion");
        if (lStrVersion.split(".").length > 2) {
            lStrVersion = lStrVersion.split(".")[0] + "." + lStrVersion.split(".")[1];
        }
        return Double.parseDouble(lStrVersion);
    }

    public static int getAPILevel() {
        String lStrAPILevel = "";
        try {
            Process lProcess = Runtime.getRuntime().exec("adb shell getprop ro.build.version.sdk");
            BufferedReader lOutput = new BufferedReader(new InputStreamReader(lProcess.getInputStream()));
            if (lOutput != null) {
                lStrAPILevel = lOutput.readLine();
                System.out.println(lStrAPILevel);
            }
        } catch (Exception e) {
            System.err.println("Unable to read API Level: " + e);
            e.printStackTrace();
        }
        System.out.println("Device API Level: " + lStrAPILevel);

        if (!lStrAPILevel.isEmpty()) {
            return Integer.parseInt(lStrAPILevel);
        } else {
            return 0;
        }
    }


}
