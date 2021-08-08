package com.maxsoft.testngtestresultsanalyzer.services;

import org.testng.ITestResult;

import java.util.*;

import static com.maxsoft.testngtestresultsanalyzer.Constants.*;
import static com.maxsoft.testngtestresultsanalyzer.ExcelFileGenerator.generateExcel;
import static org.testng.ITestResult.FAILURE;
import static org.testng.ITestResult.SKIP;

/**
 * Project Name    : maxsoft-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/4/2021
 * Time            : 2:57 PM
 * Description     : This is the excel report service class that implements the excel report generation
 **/

public class ExcelReportService {

    public Map<String, Object[]> getExcelDataMapForTestSummaryWorkSheet(List<ITestResult> testResultList) {
        Map<String, Object[]> excelDataMap = new TreeMap<>();

        excelDataMap.put("1", new Object[]{TEST_METHOD_NAME, EXECUTION_STATUS, ERROR_MESSAGE});

        testResultList.forEach(testResult ->
        {
            String error = null;

            if (testResult.getStatus() != 1) {
                error = testResult.getThrowable().toString();

                if (error.contains(NEW_LINE))
                    error = error.substring(0, error.indexOf(NEW_LINE));
            }

            excelDataMap.put(
                    String.valueOf(excelDataMap.keySet().size() + 1),
                    new Object[]{
                            testResult.getName(),
                            testResult.getStatus() == 1 ? PASSED : testResult.getStatus() == 2 ? FAILED : SKIPPED,
                            testResult.getStatus() != 1 ? error : "",
                    });
        });

        return excelDataMap;
    }

    public Map<String, Object[]> getExcelDataMapForFailureAnalysisWorkSheet(List<ITestResult> testResultList) {
        return getExcelDataMap(testResultList, FAILURE, FAILED_REASON, FAILED_TEST_COUNT);
    }

    public Map<String, Object[]> getExcelDataMapForSkippedAnalysisWorkSheet(List<ITestResult> testResultList) {
        return getExcelDataMap(testResultList, SKIP, SKIPPED_REASON, SKIPPED_TEST_COUNT);
    }

    public void generateExcelReport(Map<String, Object[]> failedTestSummaryWorkSheetDataMap,
                                    Map<String, Object[]> failureAnalysisWorkSheetDataMap,
                                    Map<String, Object[]> skippedAnalysisWorkSheetDataMap, String timestamp) {
        generateExcel(TEST_SUMMARY, failedTestSummaryWorkSheetDataMap, FAILURE_ANALYSIS, failureAnalysisWorkSheetDataMap,
                SKIPPED_ANALYSIS, skippedAnalysisWorkSheetDataMap, TEST_ANALYSIS_REPORT_DIRECTORY
                        + FILE_SEPARATOR + TEST_ANALYSIS_REPORT_FILE_NAME_PREFIX + timestamp + ".xlsx");
    }

    private Map<String, Object[]> getExcelDataMap(List<ITestResult> testResultList, int executionStatus,
                                                  String reasonColumnHeading, String testCountColumnHeading) {
        Map<String, List<String>> errorMap = new HashMap<>();

        testResultList.forEach(testResult -> {
            if (testResult.getStatus() == executionStatus) {
                String testName = testResult.getName();
                String reason = testResult.getThrowable().toString();
                if (reason.contains(NEW_LINE)) reason = reason.substring(0, reason.indexOf(NEW_LINE));

                if (errorMap.containsKey(reason)) {
                    errorMap.get(reason).add(testName);
                } else {
                    List<String> testList = new ArrayList<>();
                    testList.add(testName);
                    errorMap.put(reason, testList);
                }
            }
        });

        Map<String, Object[]> excelDataMap = new TreeMap<>();
        excelDataMap.put("1", new Object[]{reasonColumnHeading, testCountColumnHeading, TEST_METHOD_NAMES});
        errorMap.keySet().forEach(errorKey -> excelDataMap.put(
                String.valueOf(excelDataMap.keySet().size() + 1),
                new Object[]{
                        errorKey,
                        String.valueOf(errorMap.get(errorKey).size()),
                        String.join("\n", errorMap.get(errorKey)),
                }));

        return excelDataMap;
    }
}
