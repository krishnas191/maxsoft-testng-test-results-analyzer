# MaxSoft TestNG Test Results Analyzer

## Introduction
The main reason for developing this plugin is to provide an easy way to analyze the failed and skipped tests 
in test automation. 

MaxSoft TestNG Test Results Analyzer comes with a built-in failed tests grouping
mechanism. It will generate an Excel file with the failed tests against the reason. On the same Excel
file, the second tab contains the failure reasons grouping. So it would be easy to identify the tests 
which failed due to the same reason.

Further, this library has built-in Extent reporter as well. So, no need to worry about the HTML report 
generation for test executions.

#### 1. Test Analysis Report Features
> Test Summary
![image](https://user-images.githubusercontent.com/9147189/125182967-29a63c80-e230-11eb-8711-5a5fbc948f1a.png)

> Failure Analysis
![image](https://user-images.githubusercontent.com/9147189/125193741-b28e9980-e26b-11eb-9ad3-716367c69d77.png)

> Skipped Analysis
![image](https://user-images.githubusercontent.com/9147189/125183007-7b4ec700-e230-11eb-866c-68831207753b.png)

#### 2. Extent Report Features
> Dashboard
![image](https://user-images.githubusercontent.com/9147189/125170847-98eb4480-e1ce-11eb-9920-6d646fbb0013.png)

> Tests
![image](https://user-images.githubusercontent.com/9147189/125170925-1c0c9a80-e1cf-11eb-8a93-1fc082faab0c.png)

> Categories
![image](https://user-images.githubusercontent.com/9147189/125170962-43636780-e1cf-11eb-9767-1ef11709a687.png)

> Exceptions
![image](https://user-images.githubusercontent.com/9147189/125170987-6261f980-e1cf-11eb-8cbc-1787e07f634f.png)

## Advantages
- Automatically generates the Test Analysis Report after the test execution.
- Automatically generates the Extent Report after the test execution.
- Reporter details can be configured through a property file.
- No need to implement classes for Extent reporter or Test Analysis reporter.
- Simple and easy to use.

## Technologies/Frameworks Used
- Java
- TestNG
- Apache POI  
- Extent Report
- Selenium
- Apache Maven

## Supported Platforms
- Windows
- Linux
- Mac OS

## Supported Language
- Java

## Supported Test Runner
- TestNG

## How to use
**Pre-Requisites:**
1. Java
2. Maven

**Steps:**
1. Add "**MaxSoft TestNG Test Results Analyzer**" dependency into "**pom.xml**".
```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
	
    <dependencies>
        <dependency>
            <groupId>com.github.osandadeshan</groupId>
            <artifactId>maxsoft-testng-test-results-analyzer</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
```

2. Create "**test-results-analyzer.properties**" in "***src/test/resources***".
```xml
# Test Analyzer Report Configs
extent_full_report_dir=./reports/html-reports
extent_screenshots_dir=./reports/html-reports/screenshots
test_analysis_reports_dir=./reports/test-analysis-reports
extent_report_file_name_prefix=test_execution_results_
test_analysis_report_file_name_prefix=test_analysis_report_

# Extent Report Configs
extent_reporter_theme=dark
extent_document_title=Test Execution Report
extent_reporter_name=Test Execution Report
application_name=AutomationPractice.com
environment=Production
browser=Chrome
operating_system=Windows 10 - 64 Bit
test_developer=Osanda Nimalarathna
```

3. In the test automation code, find the place you are launching the WebDriver.

4. Pass your WebDriver object to the "**setDriver()**" method which can be imported from "***import static com.maxsoft.testngtestresultsanalyzer.DriverHolder.setDriver***".
```java
WebDriver driver = new ChromeDriver();
setDriver(driver);
```

5.  Update the places where your are using WebDriver object, into "**getDriver()**" method which can be imported from "***import static com.maxsoft.testngtestresultsanalyzer.DriverHolder.getDriver***".
```java
getDriver().manage().window().maximize();
```

6. An example test class.

```java
package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.maxsoft.testngtestresultsanalyzer.annotations.Category;

import static com.maxsoft.testngtestresultsanalyzer.DriverHolder.getDriver;
import static com.maxsoft.testngtestresultsanalyzer.DriverHolder.setDriver;
import static com.maxsoft.testngtestresultsanalyzer.PropertyFileReader.getProperty;
import static org.testng.Assert.assertEquals;

/**
 * Project Name    : maxsoft-testng-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 07/10/2021
 * Time            : 1:08 PM
 * Description     : This is a test class to test the login functionality
 **/

public class LoginTest {

    private WebElement emailTextBox;
    private WebElement passwordTextBox;
    private WebElement signInButton;

    @BeforeMethod
    public void before() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        setDriver(driver);
        getDriver().manage().window().maximize();
        getDriver().get(getProperty("application_url"));
        emailTextBox = getDriver().findElement(By.id("email"));
        passwordTextBox = getDriver().findElement(By.id("passwd"));
        signInButton = getDriver().findElement(By.id("SubmitLogin"));
    }

    @Category("Login")
    @Test(description = "Verify that a valid user can login to the application")
    public void testValidLogin() {
        emailTextBox.sendKeys("osanda@mailinator.com");
        passwordTextBox.sendKeys("1qaz2wsx@");
        signInButton.click();
        assertEquals(getDriver().findElement(By.xpath("//div[@class='header_user_info']//span")).getText(), "Osanda Nimalarathna");
    }

    @Category("Login")
    @Test(description = "Verify that an invalid user cannot login to the application")
    public void testInvalidLogin() {
        emailTextBox.sendKeys("osanda@mailinator.com");
        passwordTextBox.sendKeys("1qaz2wsx@");
        signInButton.click();
        assertEquals(getDriver().getTitle(), "Login - My Store");
    }

    @AfterMethod
    public void after() {
        getDriver().quit();
    }
}

```

7. Create the "**testng.xml**" by adding the "**com.maxsoft.testngtestresultsanalyzer.ReportListener**" listener class.

```xml
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="Regression Test Suite">
    <listeners>
        <listener class-name="com.maxsoft.testngtestresultsanalyzer.ReportListener"/>
    </listeners>
    <test name="Regression Test">
        <classes>
            <class name="tests.LoginTest"/>
        </classes>
    </test>
</suite>
```

8. Run the "**testng.xml**".

## License
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/License_icon-mit-2.svg/2000px-License_icon-mit-2.svg.png" alt="MIT License" width="100" height="100"/> [MaxSoft TestNG Test Results Analyzer](https://medium.com/maxsoft-testng-test-results-analyzer) is released under [MIT License](https://opensource.org/licenses/MIT)

## Copyright
Copyright 2021 MaxSoft.
