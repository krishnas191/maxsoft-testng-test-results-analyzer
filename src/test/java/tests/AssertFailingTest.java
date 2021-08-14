package tests;

import com.maxsoft.testngtestresultsanalyzer.TestAnalyzeReportListener;
import com.maxsoft.testngtestresultsanalyzer.annotations.Category;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Project Name    : maxsoft-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/3/2021
 * Time            : 12:37 PM
 * Description     : This is the Assertion failing class to simulate test failures due to assertion errors
 **/

@SuppressWarnings("ALL")
@Listeners(TestAnalyzeReportListener.class)
public class AssertFailingTest {

    @Category("AssertFail")
    @Test(description = "fail() test simulation 1")
    public void testAssertFailingMethod1() {
        fail("fail() test simulation 1");
    }

    @Category("AssertFail")
    @Test(description = "fail() test simulation 2")
    public void testAssertFailingMethod2() {
        fail("fail() test simulation 2");
    }

    @Category("AssertFail")
    @Test(description = "fail() test simulation 3")
    public void testAssertFailingMethod3() {
        fail("fail() test simulation 2");
    }

    @Category("AssertFail")
    @Test(description = "fail() test simulation 4")
    public void testAssertFailingMethod4() {
        fail("fail() test simulation 3");
    }

    @Category("AssertFail")
    @Test(description = "fail() test simulation 5")
    public void testAssertFailingMethod5() {
        fail("fail() test simulation 3");
    }

    @Category("AssertFail")
    @Test(description = "fail() test simulation 6")
    public void testAssertFailingMethod6() {
        fail("fail() test simulation 3");
    }

    @Category("AssertEqualsFail")
    @Test(description = "assertEquals() Failing test simulation 1")
    public void testAssertEqualsFailingMethod1() {
        assertEquals(1, 2);
    }

    @Category("AssertEqualsFail")
    @Test(description = "assertEquals() Failing test simulation 2")
    public void testAssertEqualsFailingMethod2() {
        assertEquals(1, 2);
    }

    @Category("AssertEqualsFail")
    @Test(description = "assertEquals() Failing test simulation 3")
    public void testAssertEqualsFailingMethod3() {
        assertEquals(1, 2);
    }

    @Category("AssertEqualsFail")
    @Test(description = "assertEquals() Failing test simulation 4")
    public void testAssertEqualsFailingMethod4() {
        assertEquals(1, 2);
    }

    @Category("AssertTrueFail")
    @Test(description = "assertTrue() Failing test simulation 1")
    public void testAssertTrueFailingMethod1() {
        assertTrue(false);
    }

    @Category("AssertTrueFail")
    @Test(description = "assertTrue() Failing test simulation 2")
    public void testAssertTrueFailingMethod2() {
        assertTrue(false);
    }

    @Category("AssertNotNullFail")
    @Test(description = "assertNotNull() Failing test simulation 1")
    public void testAssertNotNullFailingMethod1() {
        assertNotNull(null);
    }

    @Category("AssertNotNullFail")
    @Test(description = "assertNotNull() Failing test simulation 2")
    public void testAssertNotNullFailingMethod2() {
        assertNotNull(null);
    }
}
