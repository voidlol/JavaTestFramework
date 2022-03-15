package ru.liga.main;

public class MyAssertion {

    public static void assertEquals(Object actual, Object expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError("Objects are not equal! " + actual + " " + expected );
        }
    }

    public static void assertTrue(Boolean bool) {
        if (!bool) {
            throw new AssertionError(bool + " is not true!");
        }
    }

    public static void assertNotNull(Object o) {
        if (o == null) {
            throw new AssertionError("Object is null!");
        }
    }
}
