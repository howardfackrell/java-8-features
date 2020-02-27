package com.hlf.java8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    @Test
    @DisplayName("single test 1=== 1")
    public void singleTestOneEqOne() {
        assertEquals(1, 1);
    }

    @RepeatedTest(value = 3, name="{currentRepetition}/{totalRepetitions}")
    @DisplayName("1 === 1")
    public void repeatedTestOneEqOne() {
        assertEquals(1, 1);
    }

    @Nested
    @DisplayName("Also for 2")
    class Twos {
        @Test
        @DisplayName("2 === 2")
        public void twoEqTwo( ) {
            assertEquals(2,2);
        }
    }

}
