package io.home.junit5app.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Calculator methods")
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("Test addition of positive integers")
    void add() {
        assertEquals(10, calculator.add(5, 5));
    }

    @Test
    @DisplayName("Test subtraction of positive integers")
    void subtract() {
        assertEquals(0, calculator.subtract(5, 5));
    }

    @Test
    @DisplayName("Test division of positive integers")
    void divide() {
        assertEquals(1, calculator.divide(5, 5));
    }

    @Test
    @DisplayName("Test multiplication of positive integers")
    void multiply() {
        assertEquals(25, calculator.multiply(5, 5));
    }

    @Nested
    @DisplayName("Exception cases")
    class ExceptionCases {
        @Test
        @DisplayName("Test addition of negative integers")
        void negativeNumAddTest() {
            RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.add(-5, 5));
            assertEquals("Negative numbers are not allowed", exception.getMessage());
        }

        @Test
        @DisplayName("Test division of with zero as denominator")
        void divideByZeroTest() {
            RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.divide(5, 0));
            assertEquals("Zero is not allowed in denominator", exception.getMessage());
        }

        @RepeatedTest(5)
        @DisplayName("Repeat test for timings")
        void multiply() {
            int output = assertTimeout(Duration.ofMillis(1001), () -> calculator.multiplyWithDelay(5, 5));
            assertEquals(25, output);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "2", ""})
    @NullSource
    @EmptySource
    //@NullAndEmptySource
    void divisionsTest(String str) {
        assertNotEquals("STOP", str);
    }
}