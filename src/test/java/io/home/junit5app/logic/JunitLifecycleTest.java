package io.home.junit5app.logic;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class JunitLifecycleTest {

    private final Demo demo = new Demo();

    @BeforeAll
    static void initAll() {
        System.out.println("Before All");
    }

    @BeforeEach
    void init() {
        System.out.println("Before Each");
    }

    @Test
    void succeedingTest() {
        System.out.println("Succeeding Test");
    }

    @Test
    void failingTest() {
        System.out.println("Failing Test");
        fail("A failing test");
    }

    @Test
    @Disabled("For demonstration purposes")
    void skippedTest() {
        System.out.println("Skipped Test");
    }

    @Test
    void abortedTest() {
        assumeTrue(demo.returnMessage().contains("123"));
        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After Each");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("After All");
    }
}