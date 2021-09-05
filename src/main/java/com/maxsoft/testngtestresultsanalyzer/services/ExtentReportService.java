package com.maxsoft.testngtestresultsanalyzer.services;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;

import static com.aventstack.extentreports.reporter.configuration.ViewName.*;
import static com.maxsoft.testngtestresultsanalyzer.Constants.*;
import static com.maxsoft.testngtestresultsanalyzer.DriverHolder.getDriver;
import static com.maxsoft.testngtestresultsanalyzer.PropertyFileReader.getProperty;
import static com.maxsoft.testngtestresultsanalyzer.ThrowableHelper.getErrorMessage;
import static com.maxsoft.testngtestresultsanalyzer.annotations.AnnotationReader.getTestMethodCategory;

/**
 * Project Name    : maxsoft-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/4/2021
 * Time            : 3:40 PM
 * Description     : This is the extent report service class that implements the extent report generation
 **/

public class ExtentReportService {

    private static final ExtentReports extentReports = new ExtentReports();

    public void initializeExtentReporter(String timestamp) {
        ExtentSparkReporter sparkAllTestsReporter = new ExtentSparkReporter(EXTENT_FULL_REPORT_DIRECTORY
                + FILE_SEPARATOR + EXTENT_REPORT_FILE_NAME_PREFIX + timestamp + ".html")
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{DASHBOARD, TEST, CATEGORY, EXCEPTION})
                .apply();

        extentReports.attachReporter(sparkAllTestsReporter);

        try {

            if (getProperty("extent_reporter_theme").equalsIgnoreCase("dark"))
                sparkAllTestsReporter.config().setTheme(Theme.DARK);
            else
                sparkAllTestsReporter.config().setTheme(Theme.STANDARD);

            sparkAllTestsReporter.config().setDocumentTitle(getProperty("extent_document_title"));
            sparkAllTestsReporter.config().setReportName(getProperty("extent_reporter_name"));

            extentReports.setSystemInfo("Application Name", getProperty("application_name"));
            extentReports.setSystemInfo("Environment", getProperty("environment"));
            extentReports.setSystemInfo("Browser", getProperty("browser"));
            extentReports.setSystemInfo("Operating System", getProperty("operating_system"));
            extentReports.setSystemInfo("Test Developer", getProperty("test_developer"));

        } catch (Exception ex) {
            sparkAllTestsReporter.config().setTheme(Theme.STANDARD);
        }
    }

    public void updateExtentReportOnTestSuccess(ITestResult iTestResult) {
        String iTestDescription = iTestResult.getMethod().getDescription();

        ExtentTest passedTest = extentReports.createTest(iTestResult.getName())
                .info("<b> Test Class: </b> <br />" + iTestResult.getTestClass().getName())
                .info("<b> Test Method Name: </b> <br />" + iTestResult.getName());

        if (iTestDescription != null)
            passedTest.info("<b> Test Method Description: </b> <br />" + iTestDescription);

        String category = getTestMethodCategory(iTestResult.getTestClass().getRealClass(), iTestResult.getName());

        if (category != null)
            passedTest.assignCategory(category);
    }

    public void updateExtentReportOnTestFailure(ITestResult iTestResult, String timestamp) {
        String iTestDescription = iTestResult.getMethod().getDescription();

        ExtentTest failedTest = extentReports.createTest(iTestResult.getName())
                .info("<b> Test Class: </b> <br />" + iTestResult.getTestClass().getName())
                .info("<b> Test Method Name: </b> <br />" + iTestResult.getName());

        if (iTestDescription != null)
            failedTest.info("<b> Test Method Description: </b> <br />" + iTestDescription);

        failedTest
                .createNode("<b> Error Details: </b>")
                .fail("<b> Error Message: </b> <br />" + getErrorMessage(iTestResult.getThrowable()))
                .fail(iTestResult.getThrowable());

        String screenshotPath = takeScreenshotAndReturnFilePath(iTestResult.getName(), timestamp);

        if (screenshotPath != null)
            failedTest
                    .createNode("<b> Screenshot: </b>")
                    .fail(MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        String category = getTestMethodCategory(iTestResult.getTestClass().getRealClass(), iTestResult.getName());

        if (category != null)
            failedTest.assignCategory(category);
    }

    public void updateExtentReportOnTestSkipped(ITestResult iTestResult) {
        String iTestDescription = iTestResult.getMethod().getDescription();

        ExtentTest skippedTest = extentReports.createTest(iTestResult.getName())
                .info("<b> Test Class: </b> <br />" + iTestResult.getTestClass().getName())
                .info("<b> Test Method Name: </b> <br />" + iTestResult.getName());

        if (iTestDescription != null)
            skippedTest.info("<b> Test Method Description: </b> <br />" + iTestDescription);

        skippedTest
                .createNode("<b> Error Details: </b>")
                .skip("<b> Error Message: </b> <br />" + getErrorMessage(iTestResult.getThrowable()))
                .skip(iTestResult.getThrowable());

        String category = getTestMethodCategory(iTestResult.getTestClass().getRealClass(), iTestResult.getName());

        if (category != null)
            skippedTest.assignCategory(category);
    }

    public void flushExtentReport() {
        extentReports.flush();
    }

    private String takeScreenshotAndReturnFilePath(String screenshotName, String timestamp) {
        String relativePath = null;
        if (getDriver() != null) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
                File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String destination = SCREENSHOTS_DIRECTORY + FILE_SEPARATOR + screenshotName + " - " + timestamp + ".png";
                relativePath = SCREENSHOTS_DIRECTORY.substring(SCREENSHOTS_DIRECTORY.lastIndexOf('/') + 1).trim()
                        + FILE_SEPARATOR + screenshotName + " - " + timestamp + ".png";
                File finalDestination = new File(destination);
                FileUtils.copyFile(source, finalDestination);
            } catch (Exception e) {
                if (!e.getMessage().contains("Session ID is null. Using WebDriver after calling quit()?"))
                    e.printStackTrace();
            }
        }
        return relativePath;
    }
}
