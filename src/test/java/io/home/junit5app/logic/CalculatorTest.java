package io.home.junit5app.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Test Calculator methods")
class CalculatorTest {

    public CalculatorTest() {
        System.out.println("CalculatorTest instance created.");
    }

    private final Calculator calculator = new Calculator();

    @Test
    @Order(4)
    @DisplayName("Test addition of positive integers")
    void add() {
        assertEquals(10, calculator.add(5, 5));
    }

    @Test
    @Order(3)
    @DisplayName("Test subtraction of positive integers")
    void subtract() {
        assertEquals(0, calculator.subtract(5, 5));
    }

    @Test
    @Order(2)
    @DisplayName("Test division of positive integers")
    void divide() {
        assertEquals(1, calculator.divide(5, 5));
    }

    @Test
    @Order(1)
    @DisplayName("Test multiplication of positive integers")
    void multiply() {
        assertEquals(25, calculator.multiply(5, 5));
    }

    @Nested
    @DisplayName("Exception cases")
    class ExceptionCases {
        @Test
        @Order(6)
        @DisplayName("Test addition of negative integers")
        void negativeNumAddTest() {
            RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.add(-5, 5));
            assertEquals("Negative numbers are not allowed", exception.getMessage());
        }

        @Test
        @Order(5)
        @DisplayName("Test division of with zero as denominator")
        void divideByZeroTest() {
            RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.divide(5, 0));
            assertEquals("Zero is not allowed in denominator", exception.getMessage());
        }

        @RepeatedTest(5)
        @DisplayName("Repeat test for timings")
        void repeatMultiplyTest() {
            int output = assertTimeout(Duration.ofMillis(1001), () -> calculator.multiplyWithDelay(5, 5));
            assertEquals(25, output);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "2", ""})
    @NullSource
    @EmptySource
    //@NullAndEmptySource
    @DisplayName("Test same test case with different values")
    void multiValuedAssertionTest(String str) {
        assertNotEquals("STOP", str);
    }

    static IntStream intRange() {
        return IntStream.range(0, 10);
    }

    @ParameterizedTest
    @MethodSource("intRange")
    @DisplayName("Test with steam of integers coming from method output")
    void methodSourceAssertionTest(Integer i) {
        assertNotNull(i);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",
        "4, 5, 9",
        "10, 45, 55"
    })
    @DisplayName("Test with predefined input & output data")
    void predefinedCaseValuesTest(int num1, int num2, int expectedOutput) {
        assertEquals(expectedOutput, calculator.add(num1, num2));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-cases.csv", numLinesToSkip = 1)
    @DisplayName("Test with predefined input & output data from CSV file")
    void predefinedCaseValuesFromCsvFileTest(int num1, int num2, int expectedOutput) {
        assertEquals(expectedOutput, calculator.add(num1, num2));
    }
}