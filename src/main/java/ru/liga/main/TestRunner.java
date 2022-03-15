package ru.liga.main;

import ru.liga.annotation.After;
import ru.liga.annotation.Before;
import ru.liga.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public List<TestResult> runTest(String className) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        List<TestResult> resultList = new ArrayList<>();
        try {
            Class<?> classType = loader.loadClass(className);
            Object instance = classType.newInstance();
            Method[] methods = classType.getMethods();
            List<Method> tests = new ArrayList<>();
            List<Method> beforeTests = new ArrayList<>();
            List<Method> afterTests = new ArrayList<>();
            for (Method method : methods) {
                if (method.getAnnotation(Test.class) != null) {
                    tests.add(method);
                }
                if (method.getAnnotation(Before.class) != null) {
                    beforeTests.add(method);
                }
                if (method.getAnnotation(After.class) != null) {
                    afterTests.add(method);
                }
            }
            beforeTests.forEach(m -> runMethod(m, instance));
            tests.forEach(t -> resultList.add(runTest(t, instance)));
            afterTests.forEach(m -> runMethod(m, instance));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }


    private TestResult runTest(Method testMethod, Object o) {
        TestStatus status = TestStatus.PASSED;
        Throwable stackTrace = null;
        try {
            testMethod.invoke(o);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof AssertionError) {
                status = TestStatus.FAILED;
                stackTrace = e.getCause();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return new TestResult(testMethod, status, stackTrace);
    }

    private void runMethod(Method methodToRun, Object o) {
        try {
            methodToRun.invoke(o);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
