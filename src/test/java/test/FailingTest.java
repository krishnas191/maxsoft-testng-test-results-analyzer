package test;

import com.maxsoft.autotesttroubleshoothelper.ReportListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

/**
 * Project Name    : auto-test-troubleshoot-helper
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/3/2021
 * Time            : 12:37 PM
 * Description     : This is the failing class to simulate test failures
 **/

@Listeners(ReportListener.class)
public class FailingTest {

    @Test(description = "Failing test simulation 1")
    public void testFailedMethod1() {
        fail("Failing test simulation 1");
    }

    @Test(description = "Failing test simulation 2")
    public void testFailedMethod2() {
        fail("Failing test simulation 2");
    }

    @Test(description = "Failing test simulation 3")
    public void testFailedMethod3() {
        fail("Failing test simulation 2");
    }

    @Test(description = "Failing test simulation 4")
    public void testFailedMethod4() {
        fail("Failing test simulation 3");
    }

    @Test(description = "Failing test simulation 5")
    public void testFailedMethod5() {
        fail("Failing test simulation 3");
    }

    @Test(description = "Failing test simulation 6")
    public void testFailedMethod6() {
        fail("Failing test simulation 3");
    }
}
