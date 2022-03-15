package ru.liga.main;


import java.util.List;

public class App {

    public static void main(String[] args) {
        TestRunner runner = new TestRunner();
        List<TestResult> resultList = runner.runTest("ru.liga.main.SomeTests");
        checkTestResults(resultList);
    }

    private static void checkTestResults(List<TestResult> testResults) {
        for (TestResult result : testResults) {
            if (result.getTestStatus() == TestStatus.PASSED) {
                System.out.printf("Test %s is passed!\n", result.getTestedMethod().getName());
            } else {
                System.err.printf("Test %s is FAILED!\n%s\n", result.getTestedMethod().getName(), result.getTestMethodStackTrace().getMessage());
            }
        }
    }
}
