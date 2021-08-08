package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Project Name    : maxsoft-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/2/2021
 * Time            : 6:17 PM
 * Description     : This is the home page class that contains all concrete methods of the home page
 **/

public class HomePage {

    private final By profileNameLabel = By.xpath("//span[@itemprop='name']");
    private final By usernameLabel = By.xpath("//span[@itemprop='additionalName']");
    private final By invalidLocator = By.xpath("//span[@itemprop='name1']");

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProfileName() {
        return driver.findElement(profileNameLabel).getText().trim();
    }

    public String getUsername() {
        return driver.findElement(usernameLabel).getText().trim();
    }

    public String getInvalidLocator() {
        return driver.findElement(invalidLocator).getText();
    }
}
