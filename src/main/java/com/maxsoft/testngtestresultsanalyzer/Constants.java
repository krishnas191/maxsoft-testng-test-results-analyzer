package com.maxsoft.testngtestresultsanalyzer;

import java.io.File;

import static com.maxsoft.testngtestresultsanalyzer.PropertyFileReader.getProperty;

/**
 * Project Name    : maxsoft-test-results-analyzer
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
    public static final String TEST_RESULTS_ANALYZER_PROPERTY_FILE_DIRECTORY = PROJECT_DIRECTORY + FILE_SEPARATOR
            + "src" + FILE_SEPARATOR + "main" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR
            + "test-results-analyzer.properties";
    public static final String EXTENT_FULL_REPORT_DIRECTORY = getProperty("extent_full_report_dir");
    public static final String SCREENSHOTS_DIRECTORY = getProperty("extent_screenshots_dir");
    public static final String TEST_ANALYSIS_REPORT_DIRECTORY = getProperty("test_analysis_reports_dir");

    // Reporter constants
    public static final String EXTENT_REPORT_FILE_NAME_PREFIX = getProperty("extent_report_file_name_prefix");
    public static final String TEST_ANALYSIS_REPORT_FILE_NAME_PREFIX = getProperty("test_analysis_report_file_name_prefix");

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
    public static final String FAILED_TEST_ANALYSIS_BAR_CHART_NAME = "Failed Test Analysis - Bar Chart";
    public static final String FAILED_TEST_ANALYSIS_PIE_CHART_NAME = "Failed Test Analysis - Pie Chart";

    // Skipped Analysis worksheet constants
    public static final String SKIPPED_REASON = "Skipped Reason";
    public static final String SKIPPED_TEST_COUNT = "Skipped Test Count";
    public static final String SKIPPED_TEST_ANALYSIS_BAR_CHART_NAME = "Skipped Test Analysis - Bar Chart";
    public static final String SKIPPED_TEST_ANALYSIS_PIE_CHART_NAME = "Skipped Test Analysis - Pie Chart";

    public static final String NEW_LINE = "\n";
}
