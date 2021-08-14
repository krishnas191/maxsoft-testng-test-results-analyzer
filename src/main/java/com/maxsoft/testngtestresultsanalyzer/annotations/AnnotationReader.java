package com.maxsoft.testngtestresultsanalyzer.annotations;

import java.lang.reflect.Method;

/**
 * Project Name    : maxsoft-test-results-analyzer
 * Developer       : Osanda Deshan
 * Version         : 1.0.0
 * Date            : 7/10/2021
 * Time            : 12:34 PM
 * Description     : This is the annotation reader class that can used to read values of the annotations on test methods
 **/

public class AnnotationReader {

    public static String getTestMethodCategory(Class<?> testClass, String testMethodName) {
        String categoryName = null;
        try {
            Method method = testClass.getMethod(testMethodName);
            Category category = method.getAnnotation(Category.class);
            if (category != null) categoryName = category.value();
        } catch (NoSuchMethodException ignored) {
        }
        return categoryName;
    }
}
