package com.maxsoft.testngtestresultsanalyzer;

import org.openqa.selenium.WebDriverException;

import static com.maxsoft.testngtestresultsanalyzer.Constants.NEW_LINE;

/**
 * Project Name    : maxsoft-testng-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 8/11/2021
 * Time            : 9:40 PM
 * Description     : This is the throwable helper class which manipulate the throwable
 **/

public class ThrowableHelper {

    public static String getErrorMessage(Throwable throwable) {
        String errorMessage = throwable.toString();

        if(throwable instanceof WebDriverException) {
            if (errorMessage.contains(NEW_LINE))
                errorMessage = errorMessage.substring(0, errorMessage.indexOf(NEW_LINE));
        }
        return errorMessage;
    }
}
