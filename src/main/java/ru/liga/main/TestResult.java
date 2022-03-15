package ru.liga.main;


import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public class TestResult {

    private final Method testedMethod;
    private final TestStatus testStatus;
    private final Throwable testMethodStackTrace;

    public TestResult(Method testedMethod, TestStatus testStatus, Throwable testMethodStackTrace) {
        this.testedMethod = testedMethod;
        this.testStatus = testStatus;
        this.testMethodStackTrace = testMethodStackTrace;
    }
}
