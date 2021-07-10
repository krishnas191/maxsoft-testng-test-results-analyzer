package com.maxsoft.testresultsanalyzer;

import com.maxsoft.testresultsanalyzer.services.ExcelReportService;
import com.maxsoft.testresultsanalyzer.services.ExtentReportService;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Project Name    : maxsoft-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 07/02/2021
 * Time            : 12:38 PM
 * Description     : This is the listener class that used to listen to the TestNG test execution and create the reports
 **/

public class ReportListener implements ITestListener {

    private static final String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    private final List<ITestResult> testResultsList = new ArrayList<>();

    private ExtentReportService extentReportService;
    private ExcelReportService excelReportService;

    @Override
    public void onStart(ITestContext iTestContext) {
        extentReportService = new ExtentReportService();
        extentReportService.getExtentReporter(timestamp);
        excelReportService = new ExcelReportService();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentReportService.updateExtentReportOnTestSuccess(iTestResult);
        testResultsList.add(iTestResult);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        extentReportService.updateExtentReportOnTestFailure(iTestResult, timestamp);
        testResultsList.add(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        extentReportService.updateExtentReportOnTestSkipped(iTestResult);
        testResultsList.add(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReportService.flushExtentReport();

        Map<String, Object[]> failedTestSummaryWorkSheetDataMap = excelReportService
                .getExcelDataMapForTestSummaryWorkSheet(testResultsList);
        Map<String, Object[]> failureAnalysisWorkSheetDataMap = excelReportService
                .getExcelDataMapForFailureAnalysisWorkSheet(testResultsList);
        Map<String, Object[]> skippedAnalysisWorkSheetDataMap = excelReportService
                .getExcelDataMapForSkippedAnalysisWorkSheet(testResultsList);

        excelReportService
                .generateExcelReport(failedTestSummaryWorkSheetDataMap, failureAnalysisWorkSheetDataMap,
                        skippedAnalysisWorkSheetDataMap, timestamp);
    }
}
