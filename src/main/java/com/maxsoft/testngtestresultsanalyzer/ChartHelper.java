package com.maxsoft.testngtestresultsanalyzer;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Map;

import static org.apache.poi.xddf.usermodel.chart.ChartTypes.BAR;
import static org.apache.poi.xddf.usermodel.chart.ChartTypes.PIE;

/**
 * Project Name    : maxsoft-testng-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 9/26/2021
 * Time            : 12:25 PM
 * Description     : This is the Chart helper class that is used to generate bar charts and pie charts for the Excel file
 **/

public class ChartHelper {

    private final XSSFWorkbook workbook;

    public ChartHelper(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public void generateCharts(String sheetName, Map<String, Object[]> excelDataMap,
                               String barChartTitle, String pieChartTitle,
                               String xAxisLabel, String yAxisLabel) {
        if (excelDataMap.size() > 1) {
            prepareBarChart(sheetName, excelDataMap, barChartTitle, xAxisLabel, yAxisLabel);
            preparePieChart(sheetName, excelDataMap, pieChartTitle);
        }
    }

    private void prepareBarChart(String sheetName, Map<String, Object[]> excelDataMap, String barChartTitle,
                                 String xAxisLabel, String yAxisLabel) {

        XSSFSheet workbookSheet = workbook.getSheet(sheetName);

        int reasonsCount = excelDataMap.size() - 1;
        int charMinimumWidth = getCharMinimumWidth(excelDataMap, reasonsCount);

        XSSFChart barChart = getConfiguredChart(workbookSheet, barChartTitle, (reasonsCount + 3),
                (reasonsCount + 3) + charMinimumWidth + 3);
        XDDFChartLegend barChartLegend = barChart.getOrAddLegend();
        barChartLegend.setPosition(LegendPosition.TOP_RIGHT);

        XDDFCategoryAxis bottomAxis = barChart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle(yAxisLabel);
        XDDFValueAxis leftAxis = barChart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle(xAxisLabel);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        XDDFDataSource<String> reasons = XDDFDataSourcesFactory.fromStringCellRange(workbookSheet,
                new CellRangeAddress(1, reasonsCount, 0, 0));
        XDDFNumericalDataSource<Double> testCounts = XDDFDataSourcesFactory.fromNumericCellRange(workbookSheet,
                new CellRangeAddress(1, reasonsCount, 1, 1));

        XDDFChartData barChartData = barChart.createData(BAR, bottomAxis, leftAxis);
        XDDFChartData.Series barChartSeries = barChartData.addSeries(reasons, testCounts);
        barChartSeries.setTitle(yAxisLabel, null);
        barChartData.setVaryColors(true);
        barChart.plot(barChartData);

        setChartLabels(barChart, BAR);
        XDDFBarChartData bar = (XDDFBarChartData) barChartData;
        bar.setBarDirection(BarDirection.BAR);
    }

    private void preparePieChart(String sheetName, Map<String, Object[]> excelDataMap, String pieChartTitle) {

        XSSFSheet workbookSheet = workbook.getSheet(sheetName);

        int reasonsCount = excelDataMap.size() - 1;
        int charMinimumWidth = getCharMinimumWidth(excelDataMap, reasonsCount);

        XSSFChart pieChart = getConfiguredChart(workbookSheet, pieChartTitle,
                (reasonsCount + 3) + charMinimumWidth + 5,
                ((reasonsCount + 3) + charMinimumWidth + 5) + charMinimumWidth + 3);
        XDDFChartLegend pieChartLegend = pieChart.getOrAddLegend();
        pieChartLegend.setPosition(LegendPosition.TOP_RIGHT);

        XDDFDataSource<String> reasons = XDDFDataSourcesFactory.fromStringCellRange(workbookSheet,
                new CellRangeAddress(1, reasonsCount, 0, 0));
        XDDFNumericalDataSource<Double> testCounts = XDDFDataSourcesFactory.fromNumericCellRange(workbookSheet,
                new CellRangeAddress(1, reasonsCount, 1, 1));

        XDDFChartData pieChartData = pieChart.createData(ChartTypes.PIE, null, null);
        pieChartData.setVaryColors(true);
        pieChartData.addSeries(reasons, testCounts);
        pieChart.plot(pieChartData);

        setChartLabels(pieChart, PIE);
    }

    private int getCharMinimumWidth(Map<String, Object[]> excelDataMap, int reasonsCount) {
        return excelDataMap.size() < 4 ? 11 : reasonsCount * 3;
    }

    private XSSFChart getConfiguredChart(XSSFSheet workbookSheet, String chartTitle,
                                         int chartStartingRow, int chartEndingRow) {

        XSSFDrawing drawing = workbookSheet.createDrawingPatriarch();
        XSSFChart chart = drawing.createChart(drawing.createAnchor(0, 0, 0, 0, 0,
                chartStartingRow, 10, chartEndingRow));
        chart.setTitleText(chartTitle);
        chart.setTitleOverlay(false);

        return chart;
    }

    private void setChartLabels(XSSFChart chart, ChartTypes chartType) {
        switch (chartType) {
            case BAR:
                if (!chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(0).isSetDLbls())
                    chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(0).addNewDLbls();
                chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowLegendKey().setVal(false);
                chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowPercent().setVal(true);
                chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowLeaderLines().setVal(false);
                chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowVal().setVal(true);
                chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowCatName().setVal(false);
                chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowSerName().setVal(false);
                chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowBubbleSize().setVal(false);
                break;

            case PIE:
                if (!chart.getCTChart().getPlotArea().getPieChartArray(0).getSerArray(0).isSetDLbls())
                    chart.getCTChart().getPlotArea().getPieChartArray(0).getSerArray(0).addNewDLbls();
                chart.getCTChart().getPlotArea().getPieChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowLegendKey().setVal(false);
                chart.getCTChart().getPlotArea().getPieChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowPercent().setVal(true);
                chart.getCTChart().getPlotArea().getPieChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowLeaderLines().setVal(false);
                chart.getCTChart().getPlotArea().getPieChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowVal().setVal(false);
                chart.getCTChart().getPlotArea().getPieChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowCatName().setVal(false);
                chart.getCTChart().getPlotArea().getPieChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowSerName().setVal(false);
                chart.getCTChart().getPlotArea().getPieChartArray(0).getSerArray(0).getDLbls()
                        .addNewShowBubbleSize().setVal(false);
                break;

            default:
                throw new IllegalArgumentException("Only accepts 'BAR' or 'PIE' chart types");
        }
    }
}
