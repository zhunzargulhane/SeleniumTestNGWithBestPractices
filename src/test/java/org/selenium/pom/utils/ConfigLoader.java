package org.selenium.pom.utils;

import org.selenium.pom.constants.EnvType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static ConfigLoader configLoader = null;
    private static Properties properties = null;

    private ConfigLoader() {
        String env = System.getProperty("env", "STAGE");
        switch (EnvType.valueOf(env)) {
            case STAGE -> properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
            case PROD -> properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
            default -> throw new RuntimeException("Invalid Env provided "+env);
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public static String getBaseUrl() {
        String baseUrl = properties.getProperty("baseUrl");
        if (baseUrl != null)
            return properties.getProperty("baseUrl");
        else throw new RuntimeException("baseURl property is not present in stg_config.properties file");
    }

    public static String getUserName() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }


}
