package com.maxsoft.testresultsanalyzer.services;

import org.testng.ITestResult;

import java.util.*;

import static com.maxsoft.testresultsanalyzer.Constants.*;
import static com.maxsoft.testresultsanalyzer.ExcelFileGenerator.generateExcel;

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
        testResultList.forEach(failedTest -> excelDataMap.put(
                String.valueOf(excelDataMap.keySet().size() + 1),
                new Object[]{
                        failedTest.getName(),
                        failedTest.getStatus() == 1 ? PASSED : failedTest.getStatus() == 2 ? FAILED : SKIPPED,
                        failedTest.getStatus() != 1 ? failedTest.getThrowable().getMessage() : "",
                }));
        return excelDataMap;
    }

    public Map<String, Object[]> getExcelDataMapForFailureAnalysisWorkSheet(List<ITestResult> testResultList) {
        Map<String, List<String>> errorMap = new HashMap<>();

        testResultList.forEach(testResult -> {
            if (testResult.getStatus() == 2) {
                String testName = testResult.getName();
                String errorName = testResult.getThrowable().getMessage();

                if (errorMap.containsKey(errorName)) {
                    errorMap.get(errorName).add(testName);
                } else {
                    List<String> testList = new ArrayList<>();
                    testList.add(testName);
                    errorMap.put(errorName, testList);
                }
            }
        });

        Map<String, Object[]> excelDataMap = new TreeMap<>();
        excelDataMap.put("1", new Object[]{FAILED_REASON, FAILED_TEST_COUNT, TEST_METHOD_NAMES});
        errorMap.keySet().forEach(errorKey -> excelDataMap.put(
                String.valueOf(excelDataMap.keySet().size() + 1),
                new Object[]{
                        errorKey,
                        String.valueOf(errorMap.get(errorKey).size()),
                        String.join("\n", errorMap.get(errorKey)),
                }));

        return excelDataMap;
    }

    public Map<String, Object[]> getExcelDataMapForSkippedAnalysisWorkSheet(List<ITestResult> testResultList) {
        Map<String, List<String>> errorMap = new HashMap<>();

        testResultList.forEach(testResult -> {
            if (testResult.getStatus() == 3) {
                String testName = testResult.getName();
                String errorName = testResult.getThrowable().getMessage();

                if (errorMap.containsKey(errorName)) {
                    errorMap.get(errorName).add(testName);
                } else {
                    List<String> testList = new ArrayList<>();
                    testList.add(testName);
                    errorMap.put(errorName, testList);
                }
            }
        });

        Map<String, Object[]> excelDataMap = new TreeMap<>();
        excelDataMap.put("1", new Object[]{SKIPPED_REASON, SKIPPED_TEST_COUNT, TEST_METHOD_NAMES});
        errorMap.keySet().forEach(errorKey -> excelDataMap.put(
                String.valueOf(excelDataMap.keySet().size() + 1),
                new Object[]{
                        errorKey,
                        String.valueOf(errorMap.get(errorKey).size()),
                        String.join("\n", errorMap.get(errorKey)),
                }));

        return excelDataMap;
    }

    public void generateExcelReport(Map<String, Object[]> failedTestSummaryWorkSheetDataMap,
                                    Map<String, Object[]> failureAnalysisWorkSheetDataMap,
                                    Map<String, Object[]> skippedAnalysisWorkSheetDataMap, String timestamp) {
        generateExcel(TEST_SUMMARY, failedTestSummaryWorkSheetDataMap, FAILURE_ANALYSIS, failureAnalysisWorkSheetDataMap,
                SKIPPED_ANALYSIS, skippedAnalysisWorkSheetDataMap, TEST_ANALYSIS_REPORT_DIRECTORY
                        + FILE_SEPARATOR + TEST_ANALYSIS_REPORT_FILE_NAME_PREFIX + timestamp + ".xlsx");
    }
}
