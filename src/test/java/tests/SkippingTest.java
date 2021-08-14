package tests;

import com.maxsoft.testngtestresultsanalyzer.TestAnalyzeReportListener;
import com.maxsoft.testngtestresultsanalyzer.annotations.Category;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

/**
 * Project Name    : maxsoft-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 07/02/2021
 * Time            : 3:38 PM
 * Description     : This is the skipping class to simulate test skipping
 **/

@Listeners(TestAnalyzeReportListener.class)
public class SkippingTest {

    @BeforeMethod
    public void before() {
        fail("Failure simulated from SkippingTest.java");
    }

    @Category("Skipping")
    @Test(description = "Skipping test simulation 1")
    public void testSkippingMethod1() {
        System.out.println("'testSkippingMethod1' method will skip");
    }

    @Category("Skipping")
    @Test(description = "Skipping test simulation 2")
    public void testSkippingMethod2() {
        System.out.println("'testSkippingMethod2' method will skip");
    }
}
