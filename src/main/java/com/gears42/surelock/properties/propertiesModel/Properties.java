
package com.gears42.surelock.properties.propertiesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

    @SerializedName("DriverInitialisation")
    @Expose
    private DriverInitialisation driverInitialisation;
    @SerializedName("TestConfiguration")
    @Expose
    private TestConfiguration testConfiguration;

    public DriverInitialisation getDriverInitialisation() {
        return driverInitialisation;
    }

    public void setDriverInitialisation(DriverInitialisation driverInitialisation) {
        this.driverInitialisation = driverInitialisation;
    }

    public TestConfiguration getTestConfiguration() {
        return testConfiguration;
    }

    public void setTestConfiguration(TestConfiguration testConfiguration) {
        this.testConfiguration = testConfiguration;
    }

}
