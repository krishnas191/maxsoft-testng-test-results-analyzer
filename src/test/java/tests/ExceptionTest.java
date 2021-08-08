package tests;

import com.maxsoft.testngtestresultsanalyzer.ReportListener;
import com.maxsoft.testngtestresultsanalyzer.annotations.Category;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Project Name    : maxsoft-testng-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 8/8/2021
 * Time            : 2:18 PM
 * Description     : This is the Exception class to simulate test exceptions
 **/

@SuppressWarnings({"ResultOfMethodCallIgnored", "ConstantConditions"})
@Listeners(ReportListener.class)
public class ExceptionTest {

    @Category("Failing category 1 in ExceptionTest class")
    @Test(description = "NullPointerException test simulation 1")
    public void testNullPointerExceptionMethod1() {
        String text = null;
        System.out.println(text.equals("NullPointer"));
    }

    @Category("Failing category 1 in ExceptionTest class")
    @Test(description = "NullPointerException test simulation 1")
    public void testNullPointerExceptionMethod2() {
        String text = null;
        System.out.println(text.equals("NullPointer"));
    }

    @Category("Failing category 2 in ExceptionTest class")
    @Test(description = "FileNotFoundException test simulation 1")
    public void testFileNotFoundExceptionMethod1() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./exception/exception.txt");
        fileInputStream.available();
    }

    @Category("Failing category 2 in ExceptionTest class")
    @Test(description = "FileNotFoundException test simulation 2")
    public void testFileNotFoundExceptionMethod2() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./exception/exception.txt");
        fileInputStream.available();
    }

    @Category("Failing category 2 in ExceptionTest class")
    @Test(description = "FileNotFoundException test simulation 3")
    public void testFileNotFoundExceptionMethod3() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./exception/exception.txt");
        fileInputStream.available();
    }
}
