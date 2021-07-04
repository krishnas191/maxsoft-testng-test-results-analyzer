package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Project Name    : auto-test-troubleshoot-helper
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/2/2021
 * Time            : 6:17 PM
 * Description     : This is the login page class that contains all concrete methods of the login page
 **/

public class LoginPage {

    private final By emailTextBox = By.id("email");
    private final By passwordTextBox = By.id("passwd");
    private final By signInButton = By.id("SubmitLogin");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        driver.findElement(emailTextBox).sendKeys(email);
        driver.findElement(passwordTextBox).sendKeys(password);
        driver.findElement(signInButton).click();
    }
}
