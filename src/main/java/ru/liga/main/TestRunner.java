package ru.liga.main;

import ru.liga.annotation.*;

import java.lang.annotation.Annotation;
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
            List<Method> tests = getMethodsForAnnotation(methods, Test.class);
            List<Method> beforeTests = getMethodsForAnnotation(methods, Before.class);
            List<Method> beforeEachTest = getMethodsForAnnotation(methods, BeforeEach.class);
            List<Method> afterTests = getMethodsForAnnotation(methods, After.class);
            List<Method> afterEachTest = getMethodsForAnnotation(methods, AfterEach.class);
            beforeTests.forEach(m -> runMethod(m, instance));
            tests.forEach(t -> {
                beforeEachTest.forEach(m -> runMethod(m, instance));
                resultList.add(runTest(t, instance));
                afterEachTest.forEach(m -> runMethod(m, instance));
            });
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

    private List<Method> getMethodsForAnnotation(Method[] methods, Class<? extends Annotation> annotationClass) {
        List<Method> listOfMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.getAnnotation(annotationClass) != null) {
                listOfMethods.add(method);
            }
        }
        return listOfMethods;
    }
}
