package com.maxsoft.autotesttroubleshoothelper;

import java.io.File;

/**
 * Project Name    : auto-test-troubleshoot-helper
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/4/2021
 * Time            : 3:31 PM
 * Description     : This is the constants class which contains static constants for the project
 **/

public class Constants {

    public static final String FILE_SEPARATOR = File.separator;
    public static final String REPORT_DIRECTORY = System.getProperty("user.dir") + FILE_SEPARATOR + "reports";
    public static final String EXTENT_REPORT_DIRECTORY = REPORT_DIRECTORY + FILE_SEPARATOR + "html-report";
}
