package io.home.junit5app.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.MethodName.class)
class TimeoutDemoTest {

    public TimeoutDemoTest() {
        System.out.println("TimeoutDemoTest instance created.");
    }

    private final TimeoutDemo timeoutDemo = new TimeoutDemo();

    @Test
    void returnMessage() {
        assertEquals("STAY SAFE!", timeoutDemo.returnMessage());
    }

    @Test
    @DisplayName("Test whole method execution timeout")
    @Timeout(value = 1005, unit = TimeUnit.MILLISECONDS)
    void incrementNumber() {
        assertEquals(11, timeoutDemo.incrementNumber(10));
    }

    @Test
    @DisplayName("Test invoked method execution timeout")
    void incrementNumberTimeoutTest() {
        int output = assertTimeout(Duration.ofMillis(1009), () -> timeoutDemo.incrementNumber(10));
        assertEquals(11, output);
    }

    @Test
    @DisplayName("Test different assertion methods")
    void testAssertions() {
        assertTrue(timeoutDemo.returnMessage().contains("S"));
        assertFalse(timeoutDemo.returnMessage().contains("1"));
        assertNotEquals("", timeoutDemo.returnMessage());
        assertNotNull(timeoutDemo.returnMessage());
    }

    @Test
    @DisplayName("Test grouped all assertion method, whether or not any assert fails")
    void testGroupedAssertions() {
        assertAll("Multiple test cases",
            () -> assertTrue(timeoutDemo.returnMessage().contains("S")),
            () -> assertFalse(timeoutDemo.returnMessage().contains("1")),
            () -> assertNotEquals("", timeoutDemo.returnMessage()),
            () -> assertNotNull(timeoutDemo.returnMessage())
        );
    }
}