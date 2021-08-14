package tests;

import com.maxsoft.testngtestresultsanalyzer.annotations.Category;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.io.FileInputStream;
import java.io.IOException;

import static com.maxsoft.testngtestresultsanalyzer.DriverHolder.getDriver;
import static org.testng.Assert.assertEquals;

/**
 * Project Name    : maxsoft-testng-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 8/8/2021
 * Time            : 2:18 PM
 * Description     : This is the Exception class to simulate test exceptions
 **/

@SuppressWarnings({"ResultOfMethodCallIgnored", "ConstantConditions"})
public class ExceptionTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void before() {
        homePage = new HomePage(getDriver());
    }

    @Category("NullPointerException")
    @Test(description = "NullPointerException test simulation 1")
    public void testNullPointerExceptionMethod1() {
        String text = null;
        System.out.println(text.equals("NullPointer"));
    }

    @Category("NullPointerException")
    @Test(description = "NullPointerException test simulation 2")
    public void testNullPointerExceptionMethod2() {
        String text = null;
        System.out.println(text.equals("NullPointer"));
    }

    @Category("FileNotFoundException")
    @Test(description = "FileNotFoundException test simulation 1")
    public void testFileNotFoundExceptionMethod1() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./exception/exception.txt");
        fileInputStream.available();
    }

    @Category("FileNotFoundException")
    @Test(description = "FileNotFoundException test simulation 2")
    public void testFileNotFoundExceptionMethod2() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./exception/exception.txt");
        fileInputStream.available();
    }

    @Category("FileNotFoundException")
    @Test(description = "FileNotFoundException test simulation 3")
    public void testFileNotFoundExceptionMethod3() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./exception/exception.txt");
        fileInputStream.available();
    }

    @Category("NoSuchElementException")
    @Test(description = "NoSuchElementException test simulation 1")
    public void testNoSuchElementExceptionMethod1() {
        assertEquals(homePage.getInvalidLocator(), "");
    }

    @Category("NoSuchElementException")
    @Test(description = "NoSuchElementException test simulation 2")
    public void testNoSuchElementExceptionMethod2() {
        assertEquals(homePage.getInvalidLocator(), "");
    }

    @Category("InvalidElementStateException")
    @Test(description = "InvalidElementStateException test simulation 1")
    public void testInvalidElementStateExceptionMethod1() {
        homePage.clickOnHeading();
    }

    @Category("InvalidElementStateException")
    @Test(description = "InvalidElementStateException test simulation 2")
    public void testInvalidElementStateExceptionMethod2() {
        homePage.clickOnHeading();
    }
}
