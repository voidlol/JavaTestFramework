package ru.liga.main;

import ru.liga.annotation.Test;

import static ru.liga.main.MyAssertion.*;

public class SomeTests {


    @Test
    public void whenNotEqualsThenError() {
        assertEquals("Hi", "Hi1");
    }

    @Test
    public void whenEqualsThenPass() {
        String a = "hi";
        String b = "hi";
        assertEquals(a, b);
    }

    @Test
    public void whenTrueThenPass() {
        int a = 5;
        assertTrue(a == 5);
    }

    @Test
    public void whenNotTrueThenError() {
        int a = 5;
        assertTrue(a == 10);
    }

    @Test
    public void whenNotNullThenPass() {
        String a = "test";
        assertNotNull(a);
    }

    @Test
    public void whenNullThenError() {
        String a = null;
        assertNotNull(a);
    }
}
