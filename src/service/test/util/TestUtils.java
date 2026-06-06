package service.test.util;

public final class TestUtils {

    private TestUtils() {
    }

    public static void assertTrue(
            boolean condition,
            String message) {

        if (!condition) {

            throw new RuntimeException(
                    "FAILED: " + message
            );
        }
    }

    public static void assertEquals(
            Object expected,
            Object actual,
            String message) {

        if (!expected.equals(actual)) {

            throw new RuntimeException(
                    "FAILED: "
                            + message
                            + " expected="
                            + expected
                            + " actual="
                            + actual
            );
        }
    }
}