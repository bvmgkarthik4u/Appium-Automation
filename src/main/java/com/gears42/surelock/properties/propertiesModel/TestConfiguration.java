
package com.gears42.surelock.properties.propertiesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestConfiguration {

    @SerializedName("testType")
    @Expose
    private String testType;
    @SerializedName("testSuite")
    @Expose
    private String testSuite;
    @SerializedName("testClass")
    @Expose
    private String testClass;

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(String testSuite) {
        this.testSuite = testSuite;
    }

    public String getTestClass() {
        return testClass;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }

}
