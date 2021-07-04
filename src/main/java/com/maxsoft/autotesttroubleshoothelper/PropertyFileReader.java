package com.maxsoft.autotesttroubleshoothelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Project Name    : auto-test-troubleshoot-helper
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 07/02/2021
 * Time            : 11:31 AM
 * Description     : This is the property file reader class that is used to read the properties inside './src/test/resources/extent.properties'
 **/

public class PropertyFileReader {

    private static final String fileSeparator = File.separator;

    public static String getProperty(String propertyName) {
        String propertyValue = null;

        try (InputStream input = new FileInputStream(System.getProperty("user.dir")  + fileSeparator + "src" +
                fileSeparator + "test" + fileSeparator + "resources" + fileSeparator + "extent.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            propertyValue = prop.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return propertyValue;
    }
}
