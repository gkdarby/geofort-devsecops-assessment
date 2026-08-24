package com.geofort.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests database connection configuration validation.
 */
final class DatabaseConnectionTest {

    /**
     * Verifies that a missing database host is rejected.
     */
    @Test
    void shouldRejectMissingDatabaseHost() {

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        DatabaseConnection::getConnection);

        assertTrue(
                exception.getMessage().contains(
                        "DB_HOST"));
    }

    /**
     * Verifies that a missing optional value uses its default.
     */
    @Test
    void shouldUseDefaultDatabasePort() {

        String port =
                DatabaseConnection.getEnvironmentVariable(
                        "GEOFORT_TEST_MISSING_PORT",
                        "3306");

        assertEquals(
                "3306",
                port);
    }

    /**
     * Verifies that an existing environment variable is returned.
     */
    @Test
    void shouldReturnExistingEnvironmentVariable() {

        String path =
                DatabaseConnection.getEnvironmentVariable(
                        "PATH",
                        "fallback-value");

        assertNotEquals(
                "fallback-value",
                path);

        assertFalse(
                path.isBlank());
    }

    /**
     * Verifies required variables reject missing values.
     */
    @Test
    void shouldRejectMissingRequiredVariable() {

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> DatabaseConnection
                                .getRequiredEnvironmentVariable(
                                        "GEOFORT_TEST_MISSING_VALUE"));

        assertTrue(
                exception.getMessage().contains(
                        "GEOFORT_TEST_MISSING_VALUE"));
    }
}
