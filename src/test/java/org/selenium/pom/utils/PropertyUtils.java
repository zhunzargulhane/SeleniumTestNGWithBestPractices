package org.selenium.pom.utils;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {
    public static Properties propertyLoader(String filePath) {
        Properties properties = new Properties();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filePath));
            try {
                properties.load(br);
                br.close();
            } catch (IOException ioException) {
                throw new RuntimeException("failed to load properties file " + filePath);
            }

        } catch (IOException e) {
            throw new RuntimeException("properties file not found " + filePath);
        }
        return properties;
    }
}
