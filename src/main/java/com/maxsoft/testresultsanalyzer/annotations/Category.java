package com.maxsoft.testresultsanalyzer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Project Name    : maxsoft-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/10/2021
 * Time            : 12:12 PM
 * Description     : This is the category annotation that can use on test methods
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Category {
    String value();
}
