
package com.gears42.surelock.properties.propertiesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverInitialisation {

    @SerializedName("adbPath")
    @Expose
    private String adbPath;
    @SerializedName("SLAPKPath")
    @Expose
    private String sLAPKPath;
    @SerializedName("appPackage")
    @Expose
    private String appPackage;
    @SerializedName("appActivity")
    @Expose
    private String appActivity;
    @SerializedName("deviceName")
    @Expose
    private String deviceName;
    @SerializedName("serverPath")
    @Expose
    private String serverPath;
    @SerializedName("noReset")
    @Expose
    private Boolean noReset;
    @SerializedName("adbExecTimeout")
    @Expose
    private Integer adbExecTimeout;
    @SerializedName("newCommandTimeout")
    @Expose
    private Integer newCommandTimeout;

    public String getAdbPath() {
        return adbPath;
    }

    public void setAdbPath(String adbPath) {
        this.adbPath = adbPath;
    }

    public String getSLAPKPath() {
        return sLAPKPath;
    }

    public void setSLAPKPath(String sLAPKPath) {
        this.sLAPKPath = sLAPKPath;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public Boolean getNoReset() {
        return noReset;
    }

    public void setNoReset(Boolean noReset) {
        this.noReset = noReset;
    }

    public Integer getAdbExecTimeout() {
        return adbExecTimeout;
    }

    public void setAdbExecTimeout(Integer adbExecTimeout) {
        this.adbExecTimeout = adbExecTimeout;
    }

    public Integer getNewCommandTimeout() {
        return newCommandTimeout;
    }

    public void setNewCommandTimeout(Integer newCommandTimeout) {
        this.newCommandTimeout = newCommandTimeout;
    }

}
