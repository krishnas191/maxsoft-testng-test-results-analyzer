package test;

import com.maxsoft.autotesttroubleshoothelper.ReportListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.maxsoft.autotesttroubleshoothelper.DriverHolder.getDriver;
import static com.maxsoft.autotesttroubleshoothelper.DriverHolder.setDriver;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Project Name    : auto-test-troubleshoot-helper
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 07/02/2021
 * Time            : 2:03 PM
 * Description     : This is the base test class that used to extend in all other test classes
 **/

@Listeners(ReportListener.class)
public class BaseTest {

    @BeforeMethod
    public void setUpBrowser() {
        WebDriverManager.chromedriver().setup();
        setDriver(new ChromeDriver());
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(30, SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(60, SECONDS);
        getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    @AfterMethod
    public void closeBrowser() {
        if (getDriver() != null)
            getDriver().quit();
    }
}
