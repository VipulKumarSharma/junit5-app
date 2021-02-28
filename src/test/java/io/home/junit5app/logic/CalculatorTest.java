package io.home.junit5app.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Calculator methods")
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("Test addition of two integers")
    void add() {
        assertEquals(10, calculator.add(5, 5));
    }

    @Test
    @DisplayName("Test addition of negative integers")
    void negativeNumAddTest() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> calculator.add(-5, 5));
        assertEquals("Negative numbers are not allowed", runtimeException.getMessage());
    }

    @Test
    void subtract() {
    }

    @Test
    void divide() {
    }

    @Test
    void multiply() {
    }
}