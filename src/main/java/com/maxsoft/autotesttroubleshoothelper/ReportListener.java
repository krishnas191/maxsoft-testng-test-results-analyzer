package com.maxsoft.autotesttroubleshoothelper;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project Name    : auto-test-troubleshoot-helper
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 07/02/2021
 * Time            : 12:38 PM
 * Description     : This is the listener class that used to listen to the TestNG test execution and create the extent report
 **/

public class ReportListener implements ITestListener {

    private static final String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    private final List<String> failedTestMethodNamesList = new ArrayList<>();
    private final List<String> failedReasonsList = new ArrayList<>();

    private ExcelReportService excelReportService;
    private ExtentReportService extentReportService;

    @Override
    public void onStart(ITestContext iTestContext) {
        extentReportService = new ExtentReportService();
        extentReportService.getExtentReporter(timestamp);
        excelReportService = new ExcelReportService();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        extentReportService.getExtentTest(iTestResult);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentReportService.updateExtentReportOnTestSuccess(iTestResult);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        extentReportService.updateExtentReportOnTestFailure(iTestResult, timestamp);

        failedTestMethodNamesList.add(iTestResult.getName());
        failedReasonsList.add(iTestResult.getThrowable().getMessage());

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        extentReportService.updateExtentReportOnTestSkipped(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReportService.flushExtentReport();

        failedTestMethodNamesList.forEach(System.out::println);
        failedReasonsList.forEach(System.out::println);

        Map<String, Object[]> failedTestSummaryWorkSheetDataMap = excelReportService
                .getExcelDataMapForFailedTestSummaryWorkSheet(failedTestMethodNamesList, failedReasonsList);
        Map<String, Object[]> failureAnalysisWorkSheetDataMap = excelReportService
                .getExcelDataMapForFailureAnalysisWorkSheet(failedTestMethodNamesList, failedReasonsList);

        Set<String> duplicateFailureReasons = findDuplicates(failedReasonsList);
        duplicateFailureReasons.forEach(System.out::println);

        excelReportService
                .generateExcelReport(failedTestSummaryWorkSheetDataMap, failureAnalysisWorkSheetDataMap, timestamp);
    }

    private Set<String> findDuplicates(List<String> listContainingDuplicates) {
        final Set<String> setToReturn = new HashSet<>();
        final Set<String> tempSet = new HashSet<>();

        for (String text : listContainingDuplicates) {
            if (!tempSet.add(text)) {
                setToReturn.add(text);
            }
        }
        return setToReturn;
    }
}
