package tests;

import com.maxsoft.testresultsanalyzer.annotations.Category;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import static com.maxsoft.testresultsanalyzer.DriverHolder.getDriver;
import static org.testng.Assert.assertEquals;

/**
 * Project Name    : maxsoft-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 07/02/2021
 * Time            : 3:38 PM
 * Description     : This is the mixed class to simulate test passing, failing and skipping
 **/

public class MixedTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void before() {
        homePage = new HomePage(getDriver());
    }

    @Category("Passing category in MixedTest class")
    @Test(description = "Passing test simulation")
    public void testPassingMethod() {
        assertEquals(homePage.getProfileName(), "Osanda Deshan Nimalarathna");
    }

    @Category("Failing category in MixedTest class")
    @Test(description = "Failing test simulation")
    public void testFailingMethod() {
        assertEquals(homePage.getProfileName(), "Osanda Deshan");
    }

    @Category("Skipping category in MixedTest class")
    @Test(description = "Skipping test simulation", dependsOnMethods = "testFailingMethod")
    public void testSkippingMethod() {
        // This method will skip
    }
}
