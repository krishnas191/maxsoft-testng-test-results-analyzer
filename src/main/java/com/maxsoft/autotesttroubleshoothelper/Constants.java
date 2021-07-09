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

    // Directory constants
    public static final String FILE_SEPARATOR = File.separator;
    public static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
    public static final String REPORT_DIRECTORY = PROJECT_DIRECTORY + FILE_SEPARATOR + "reports";
    public static final String EXTENT_PROPERTY_FILE_DIRECTORY = PROJECT_DIRECTORY  + FILE_SEPARATOR + "src" + FILE_SEPARATOR
            + "test" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "extent.properties";
    public static final String EXTENT_REPORT_DIRECTORY = REPORT_DIRECTORY + FILE_SEPARATOR + "html-report";
    public static final String SCREENSHOT_FOLDER_NAME = "screenshots";
    public static final String SCREENSHOTS_DIRECTORY = EXTENT_REPORT_DIRECTORY + FILE_SEPARATOR + SCREENSHOT_FOLDER_NAME;
    public static final String EXCEL_SUMMARY_REPORT_DIRECTORY = REPORT_DIRECTORY + FILE_SEPARATOR + "summary-report";
    public static final String EXTENT_REPORT_PREFIX = "test_execution_results_";
    public static final String EXCEL_SUMMARY_REPORT_PREFIX = "test_analysis_report_";

    // Test result constants
    public static final String PASSED = "PASSED";
    public static final String FAILED = "FAILED";
    public static final String SKIPPED = "SKIPPED";

    //Excel file worksheet constants
    public static final String TEST_SUMMARY = "Test Summary";
    public static final String FAILURE_ANALYSIS = "Failure Analysis";
    public static final String SKIPPED_ANALYSIS = "Skipped Analysis";

    // Test Summary worksheet constants
    public static final String TEST_METHOD_NAME = "Test Method Name";
    public static final String EXECUTION_STATUS = "Execution Status";
    public static final String ERROR_MESSAGE = "Error Message";

    // Failure Analysis worksheet constants
    public static final String FAILED_REASON = "Failed Reason";
    public static final String FAILED_TEST_COUNT = "Failed Test Count";
    public static final String TEST_METHOD_NAMES = "Test Method Name(s)";

    // Skipped Analysis worksheet constants
    public static final String SKIPPED_REASON = "Skipped Reason";
    public static final String SKIPPED_TEST_COUNT = "Skipped Test Count";
}
