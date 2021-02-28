package io.home.junit5app.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class DemoTest {

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
        int output = assertTimeout(Duration.ofMillis(1001), () -> timeoutDemo.incrementNumber(10));
        assertEquals(11, output);
    }

}