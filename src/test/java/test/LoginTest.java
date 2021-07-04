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
 * Description     : This is the test class to test the login functionality
 **/

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void before() {
        loginPage = new LoginPage(getDriver());
    }

    @Test(description = "Verify that a valid user can login to the application")
    public void testValidLogin() {
        loginPage.login("osanda@mailinator.com","1qaz2wsx@");
        assertEquals(getDriver().findElement(By.xpath("//div[@class='header_user_info']//span")).getText(),
                "Osanda Nimalarathna");
    }

    @Test(description = "Verify that an invalid user cannot login to the application")
    public void testInvalidLogin() {
        loginPage.login("osanda@mailinator.com","1qaz2wsx");
        assertEquals(getDriver().getTitle(), "Login - My Storee");
    }
}
