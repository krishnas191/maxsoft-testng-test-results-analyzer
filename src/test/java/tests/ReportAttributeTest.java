package tests;

import com.maxsoft.testngtestresultsanalyzer.TestAnalyzeReportListener;
import com.maxsoft.testngtestresultsanalyzer.annotations.Category;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Project Name    : maxsoft-testng-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 9/5/2021
 * Time            : 9:20 AM
 * Description     : This is the Report attribute test class to check Extent report attributes
 **/

@Listeners(TestAnalyzeReportListener.class)
public class ReportAttributeTest {

    @Test(description = "Report attribute test passing without category")
    public void testReportAttributePassingMethod1() {
        assertEquals(1, 1);
    }

    @Category("ReportAttributeTest")
    @Test()
    public void testReportAttributePassingMethod2() {
        assertEquals(1, 1);
    }

    @Test()
    public void testReportAttributePassingMethod3() {
        assertEquals(1, 1);
    }

    @Test(description = "Report attribute test failing without category")
    public void testReportAttributeFailingMethod1() {
        assertEquals(1, 2);
    }

    @Category("ReportAttributeTest")
    @Test()
    public void testReportAttributeFailingMethod2() {
        assertEquals(1, 2);
    }

    @Test()
    public void testReportAttributeFailingMethod3() {
        assertEquals(1, 2);
    }

    @Test(description = "Report attribute test skipping without category", dependsOnMethods = "testReportAttributeFailingMethod1")
    public void testReportAttributeSkippingMethod1() {
        assertEquals(1, 2);
    }

    @Category("ReportAttributeTest")
    @Test(dependsOnMethods = "testReportAttributeFailingMethod1")
    public void testReportAttributeSkippingMethod2() {
        assertEquals(1, 2);
    }

    @Test(dependsOnMethods = "testReportAttributeFailingMethod1")
    public void testReportAttributeSkippingMethod3() {
        assertEquals(1, 2);
    }
}
