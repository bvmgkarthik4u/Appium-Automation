package com.gears42.surelock.base;

import com.gears42.surelock.properties.TestProperties;
import com.gears42.surelock.properties.propertiesModel.TestConfiguration;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class TestRunner extends BaseTestInitialization {
    public static void main(String[] args) {
        TestConfiguration lTestConfiguration = TestProperties.INSTANCE.mProperties.getTestConfiguration();
        TestNG testng = new TestNG();

        if (lTestConfiguration.getTestType().equalsIgnoreCase("class")) {
            try {
                testng.setTestClasses(new Class[]{Class.forName(lTestConfiguration.getTestClass())});
                testng.run();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (lTestConfiguration.getTestType().equalsIgnoreCase("suite")) {
            List<String> suites = new ArrayList<String>();
            suites.add(".\\resources\\"+lTestConfiguration.getTestSuite());
            testng.setTestSuites(suites);
            testng.run();
        }
    }
}
