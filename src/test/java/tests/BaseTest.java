package tests;

import com.maxsoft.testngtestresultsanalyzer.ReportListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.maxsoft.testngtestresultsanalyzer.DriverHolder.getDriver;
import static com.maxsoft.testngtestresultsanalyzer.DriverHolder.setDriver;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Project Name    : maxsoft-test-results-analyzer
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
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920x1080");

        setDriver(new ChromeDriver(chromeOptions));

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(30, SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(60, SECONDS);
        getDriver().get("https://github.com/osandadeshan/");
    }

    @AfterMethod
    public void closeBrowser() {
        if (getDriver() != null)
            getDriver().quit();
    }
}
