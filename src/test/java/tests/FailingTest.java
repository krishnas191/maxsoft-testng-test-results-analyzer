package tests;

import com.maxsoft.testresultsanalyzer.ReportListener;
import com.maxsoft.testresultsanalyzer.annotations.Category;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

/**
 * Project Name    : maxsoft-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/3/2021
 * Time            : 12:37 PM
 * Description     : This is the failing class to simulate test failures
 **/

@Listeners(ReportListener.class)
public class FailingTest {

    @Category("Failing category 1 in FailingTest class")
    @Test(description = "Failing test simulation 1")
    public void testFailingMethod1() {
        fail("Failing test simulation 1");
    }

    @Category("Failing category 2 in FailingTest class")
    @Test(description = "Failing test simulation 2")
    public void testFailingMethod2() {
        fail("Failing test simulation 2");
    }

    @Category("Failing category 2 in FailingTest class")
    @Test(description = "Failing test simulation 3")
    public void testFailingMethod3() {
        fail("Failing test simulation 2");
    }

    @Category("Failing category 3 in FailingTest class")
    @Test(description = "Failing test simulation 4")
    public void testFailingMethod4() {
        fail("Failing test simulation 3");
    }

    @Category("Failing category 3 in FailingTest class")
    @Test(description = "Failing test simulation 5")
    public void testFailingMethod5() {
        fail("Failing test simulation 3");
    }

    @Category("Failing category 3 in FailingTest class")
    @Test(description = "Failing test simulation 6")
    public void testFailingMethod6() {
        fail("Failing test simulation 3");
    }
}
