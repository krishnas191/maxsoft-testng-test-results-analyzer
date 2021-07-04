package com.maxsoft.autotesttroubleshoothelper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

import static com.maxsoft.autotesttroubleshoothelper.Constants.FILE_SEPARATOR;
import static com.maxsoft.autotesttroubleshoothelper.Constants.REPORT_DIRECTORY;
import static com.maxsoft.autotesttroubleshoothelper.ExcelFileGenerator.generateExcel;

/**
 * Project Name    : auto-test-troubleshoot-helper
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/4/2021
 * Time            : 2:57 PM
 * Description     : This is the excel report service class that implements the excel report generation
 **/

public class ExcelReportService {

    public Map<String, Object[]> getExcelDataMapForFailedTestSummaryWorkSheet(List<String> failedTestMethodNamesList,
                                                                              List<String> failedReasonsList) {
        Map<String, Object[]> excelDataMap = new TreeMap<>();
        excelDataMap.put("1", new Object[]{"Test Method Name", "Error Message"});
        IntStream.range(0, failedTestMethodNamesList.size()).forEach(i -> excelDataMap.put(String.valueOf(i + 2),
                new Object[]{
                        failedTestMethodNamesList.get(i), failedReasonsList.get(i)
                }));
        return excelDataMap;
    }

    public Map<String, Object[]> getExcelDataMapForFailureAnalysisWorkSheet(List<String> failedTestMethodNamesList,
                                                                              List<String> failedReasonsList) {
        Map<String, Object[]> excelDataMap = new TreeMap<>();
        excelDataMap.put("1", new Object[]{"Error Message", "Failed Test Count", "Test Method Name(s)"});
        return excelDataMap;
    }

    public void generateExcelReport(Map<String, Object[]> failedTestSummaryWorkSheetDataMap,
                                    Map<String, Object[]> failureAnalysisWorkSheetDataMap, String timestamp) {
        generateExcel("Failed Tests Summary", failedTestSummaryWorkSheetDataMap,
                "Failure Analysis", failureAnalysisWorkSheetDataMap, REPORT_DIRECTORY
                        + FILE_SEPARATOR + "summary-report" + FILE_SEPARATOR + "test_analysis_report_" + timestamp
                        + ".xlsx");
    }
}
