package com.maxsoft.testresultsanalyzer.annotations;

import java.lang.reflect.Method;

/**
 * Project Name    : maxsoft-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/10/2021
 * Time            : 12:34 PM
 * Description     : This is the annotation reader class that can use to read values of the annotations on test methods
 **/

public class AnnotationReader {

    public static String getTestMethodCategory(Class<?> testClass, String testMethodName) {
        String categoryName;
        try {
            Method method = testClass.getMethod(testMethodName);
            Category category = method.getAnnotation(Category.class);
            categoryName = category.value();
        } catch (NoSuchMethodException e) {
            categoryName = null;
        }
        return categoryName;
    }
}
