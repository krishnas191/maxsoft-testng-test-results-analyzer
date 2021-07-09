package test;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;

import static com.maxsoft.autotesttroubleshoothelper.DriverHolder.getDriver;
import static org.testng.Assert.assertEquals;

/**
 * Project Name    : auto-test-troubleshoot-helper
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 07/02/2021
 * Time            : 3:38 PM
 * Description     : This is the passing class to simulate test passing
 **/

public class PassingTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void before() {
        loginPage = new LoginPage(getDriver());
    }

    @Test(description = "Passing test simulation 1")
    public void testPassingMethod1() {
        loginPage.login("osanda@mailinator.com","1qaz2wsx@");
        assertEquals(getDriver().findElement(By.xpath("//div[@class='header_user_info']//span")).getText(),
                "Osanda Nimalarathna");
    }

    @Test(description = "Passing test simulation 2")
    public void testPassingMethod2() {
        loginPage.login("osanda@mailinator.com","1qaz2wsx");
        assertEquals(getDriver().getTitle(), "Login - My Store");
    }
}
