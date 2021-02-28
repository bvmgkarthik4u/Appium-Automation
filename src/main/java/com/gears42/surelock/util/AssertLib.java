package com.gears42.surelock.util;

import org.testng.Assert;
import org.testng.Reporter;

public class AssertLib {
    public static void AssertEqualsMethod(String ExpectedResult, String actual, String PassStatement, String FailStatement) {
        Assert.assertEquals(actual, ExpectedResult, FailStatement);
        Reporter.log(PassStatement, true);
    }

    public static void AssertEqualsMethodBoolean(boolean ExpectedResult, boolean actual, String PassStatement, String FailStatement) {
        Assert.assertEquals(actual, ExpectedResult, FailStatement);
        Reporter.log(PassStatement, true);
    }


    public static void AssertTrueMethod(boolean value, String PassStatement, String FailStatement) {
        Assert.assertTrue(value, FailStatement);
        Reporter.log(PassStatement, true);
    }

    public static void AssertFalseMethod(boolean value, String PassStatement, String FailStatement) {
        Assert.assertFalse(value, FailStatement);
        Reporter.log(PassStatement, true);
    }

    public static void AssertEqualsMethod1(String ExpectedResult, String actual, String PassStatement) {
        Assert.assertEquals(ExpectedResult, actual);
        Reporter.log(PassStatement, true);
    }

    public static void AssertEqualsMethodInt(int ExpectedResult, int ActualResult, String PassStatement, String FailStatement) {
        Assert.assertEquals(ActualResult, ExpectedResult, FailStatement);
        Reporter.log(PassStatement, true);
    }

    public static void AssertFailMethod(String FailMessage) {
        Assert.fail(FailMessage);

        Reporter.log(FailMessage, true);
    }
}
