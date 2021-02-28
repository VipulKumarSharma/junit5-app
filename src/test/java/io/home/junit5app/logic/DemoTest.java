package io.home.junit5app.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class DemoTest {

    private final Demo demo = new Demo();

    @Test
    void returnMessage() {
        assertEquals("STAY SAFE!", demo.returnMessage());
    }

    @Test
    @DisplayName("Test whole method execution timeout")
    @Timeout(value = 1005, unit = TimeUnit.MILLISECONDS)
    void incrementNumber() {
        assertEquals(11, demo.incrementNumber(10));
    }

    @Test
    @DisplayName("Test invoked method execution timeout")
    void incrementNumberTimeoutTest() {
        int output = assertTimeout(Duration.ofMillis(1001), () -> demo.incrementNumber(10));
        assertEquals(11, output);
    }

}