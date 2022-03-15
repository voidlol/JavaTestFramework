package ru.liga.main;

import ru.liga.annotation.After;
import ru.liga.annotation.Before;
import ru.liga.annotation.Test;

import static ru.liga.main.MyAssertion.*;

public class SomeTests {

    @Before
    public void beforeAllTests() {
        System.out.println("This is before all tests...");
    }

    @After
    public void afterAllTests() {
        System.out.println("This is after all tests...");
    }

    @Test
    public void whenNotEqualsThenError() {
        System.out.println("Test1");
        assertEquals("Hi", "Hi1");
    }

    @Test
    public void whenEqualsThenPass() {
        System.out.println("Test2");
        String a = "hi";
        String b = "hi";
        assertEquals(a, b);
    }

    @Test
    public void whenTrueThenPass() {
        System.out.println("Test3");
        int a = 5;
        assertTrue(a == 5);
    }

    @Test
    public void whenNotTrueThenError() {
        System.out.println("Test4");
        int a = 5;
        assertTrue(a == 10);
    }

    @Test
    public void whenNotNullThenPass() {
        System.out.println("Test5");
        String a = "test";
        assertNotNull(a);
    }

    @Test
    public void whenNullThenError() {
        System.out.println("Test6");
        String a = null;
        assertNotNull(a);
    }
}
