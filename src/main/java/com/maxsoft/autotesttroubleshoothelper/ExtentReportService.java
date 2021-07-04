package com.maxsoft.autotesttroubleshoothelper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.maxsoft.autotesttroubleshoothelper.Constants.EXTENT_REPORT_DIRECTORY;
import static com.maxsoft.autotesttroubleshoothelper.Constants.FILE_SEPARATOR;
import static com.maxsoft.autotesttroubleshoothelper.DriverHolder.getDriver;
import static com.maxsoft.autotesttroubleshoothelper.PropertyFileReader.getProperty;

/**
 * Project Name    : auto-test-troubleshoot-helper
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/4/2021
 * Time            : 3:40 PM
 * Description     : This is the extent report service class that implements the extent report generation
 **/

public class ExtentReportService {

    private ExtentReports extent;
    private ExtentTest test;

    public void getExtentReporter(String timestamp) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTENT_REPORT_DIRECTORY + FILE_SEPARATOR
                + "test_execution_results_" + timestamp + ".html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        try {
            if (getProperty("extent_reporter_theme").equalsIgnoreCase("dark"))
                htmlReporter.config().setTheme(Theme.DARK);
            else
                htmlReporter.config().setTheme(Theme.STANDARD);

            htmlReporter.config().setDocumentTitle(getProperty("extent_document_title"));
            htmlReporter.config().setReportName(getProperty("extent_reporter_name"));

            extent.setSystemInfo("Application Name", getProperty("application_name"));
            extent.setSystemInfo("Environment", getProperty("environment"));
            extent.setSystemInfo("Browser", getProperty("browser"));
            extent.setSystemInfo("Operating System", getProperty("operating_system"));
            extent.setSystemInfo("Test Developer", getProperty("test_developer"));
        } catch (Exception ex) {
            htmlReporter.config().setTheme(Theme.DARK);
        }
    }

    public void getExtentTest(ITestResult iTestResult) {
        test = extent.createTest(iTestResult.getName(), iTestResult.getMethod().getDescription());
    }

    public void updateExtentReportOnTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS, MarkupHelper.createLabel("<b> Test Class: </b> <br />"
                + iTestResult.getTestClass().getName(), ExtentColor.TRANSPARENT));
        test.log(Status.PASS, MarkupHelper.createLabel("<b> Passed Test Method Name: </b> <br />"
                + iTestResult.getName(), ExtentColor.TRANSPARENT));
    }

    public void updateExtentReportOnTestFailure(ITestResult iTestResult, String timestamp) {
        test.log(Status.FAIL, MarkupHelper.createLabel("<b> Test Class: </b> <br />"
                + iTestResult.getTestClass().getName(), ExtentColor.TRANSPARENT));
        test.log(Status.FAIL, MarkupHelper.createLabel("<b> Failed Test Method Name: </b> <br />"
                + iTestResult.getName(), ExtentColor.TRANSPARENT));
        test.log(Status.FAIL, MarkupHelper.createLabel("<b> Error Message: </b> <br />"
                + iTestResult.getThrowable().getMessage(), ExtentColor.TRANSPARENT));
        test.log(Status.FAIL, MarkupHelper.createLabel("<b> StackTrace: </b> <br />"
                + Arrays.toString(iTestResult.getThrowable().getStackTrace()), ExtentColor.TRANSPARENT));

        if (Boolean.parseBoolean(getProperty("capture_screenshot_on_failure"))) {
            try {
                String screenshotPath = takeScreenshotAndReturnFilePath(iTestResult.getName(), timestamp);
                if (screenshotPath != null) {
                    test.log(Status.FAIL,
                            MarkupHelper.createLabel("<b> Screenshot at the failed moment is below </b>",
                            ExtentColor.TRANSPARENT));
                    test.addScreenCaptureFromPath(screenshotPath, iTestResult.getMethod().getMethodName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateExtentReportOnTestSkipped(ITestResult iTestResult) {
        test.log(Status.SKIP, MarkupHelper.createLabel("<b> Test Class: </b> <br />"
                + iTestResult.getTestClass().getName(), ExtentColor.TRANSPARENT));
        test.log(Status.SKIP, MarkupHelper.createLabel("<b> Skipped Test Method Name: </b> <br />"
                + iTestResult.getName(), ExtentColor.TRANSPARENT));
    }

    public void flushExtentReport() {
        extent.flush();
    }

    private String takeScreenshotAndReturnFilePath(String screenshotName, String timestamp) {
        String relativePath = null;
        if (getDriver() != null) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
            File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String destination = EXTENT_REPORT_DIRECTORY + FILE_SEPARATOR + "screenshots" + FILE_SEPARATOR +
                    screenshotName + " - " + timestamp + ".png";
            relativePath = "screenshots" + FILE_SEPARATOR + screenshotName + " - " + timestamp + ".png";
            File finalDestination = new File(destination);
            try {
                FileUtils.copyFile(source, finalDestination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return relativePath;
    }
}
