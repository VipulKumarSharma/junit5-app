package io.home.junit5app.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test Calculator methods")
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("Test addition of positive integers")
    void add() {
        assertEquals(10, calculator.add(5, 5));
    }

    @Test
    @DisplayName("Test addition of negative integers")
    void negativeNumAddTest() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.add(-5, 5));
        assertEquals("Negative numbers are not allowed", exception.getMessage());
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
    @DisplayName("Test division of with zero as denominator")
    void divideByZeroTest() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.divide(5, 0));
        assertEquals("Zero is not allowed in denominator", exception.getMessage());
    }

    @Test
    @DisplayName("Test multiplication of positive integers")
    void multiply() {
        assertEquals(25, calculator.multiply(5, 5));
    }
}