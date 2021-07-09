package test;

import com.maxsoft.autotesttroubleshoothelper.ReportListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

/**
 * Project Name    : auto-test-troubleshoot-helper
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 07/02/2021
 * Time            : 3:38 PM
 * Description     : This is the skipping class to simulate test skipping
 **/

@Listeners(ReportListener.class)
public class SkippingTest {

    @BeforeMethod
    public void before() {
        fail("Failure simulated from SkippingTest.java");
    }

    @Test(description = "Skipping test simulation 1")
    public void testSkippingMethod1() {
        // This method will skip
    }

    @Test(description = "Skipping test simulation 2")
    public void testSkippingMethod2() {
        // This method will skip
    }
}
