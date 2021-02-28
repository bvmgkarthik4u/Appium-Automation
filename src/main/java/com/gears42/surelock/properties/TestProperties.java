package com.gears42.surelock.properties;

import com.gears42.surelock.properties.propertiesModel.Properties;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public enum TestProperties {
    INSTANCE;
    public Properties mProperties;
    public final String PACKAGE_ID = "com.gears42.surelock";
    public final int IMP_WAIT = 30;

    TestProperties() {
        try {
            mProperties = new Gson().fromJson(readPropertyFile(), Properties.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readPropertyFile() {
        System.out.println("readPropertyFile");
        StringBuilder lStrSettings = new StringBuilder();

        File lSettingsFile = new File(".\\resources\\Properties.json");
        if (!lSettingsFile.exists()) {
            ClassLoader classLoader = getClass().getClassLoader();
            lSettingsFile = new File(classLoader.getResource("Properties.json").getFile());
        }
        System.out.println("readPropertyFile Exist :: " + lSettingsFile.exists());
        //Read text from file
        try {
            BufferedReader br = new BufferedReader(new FileReader(lSettingsFile));
            String line;
            while ((line = br.readLine()) != null) {
                lStrSettings.append(line);
                lStrSettings.append('\n');
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Properties :: " + lStrSettings.toString());
        return lStrSettings.toString();
    }

}
