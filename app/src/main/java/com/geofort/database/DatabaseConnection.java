package com.geofort.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creates connections to the MySQL database.
 */
public final class DatabaseConnection {

    /**
     * Default MySQL database port.
     */
    private static final String DEFAULT_DATABASE_PORT =
            "3306";

    /**
     * MySQL JDBC driver class.
     */
    private static final String MYSQL_DRIVER_CLASS =
            "com.mysql.cj.jdbc.Driver";

    private DatabaseConnection() {
    }

    /**
     * Creates a connection to the configured MySQL database.
     *
     * @return an active database connection
     * @throws SQLException when the connection cannot be created
     */
    public static Connection getConnection()
            throws SQLException {

        loadDatabaseDriver();

        String host =
                getRequiredEnvironmentVariable("DB_HOST");

        String port =
                getEnvironmentVariable(
                        "DB_PORT",
                        DEFAULT_DATABASE_PORT);

        String database =
                getRequiredEnvironmentVariable("DB_NAME");

        String username =
                getRequiredEnvironmentVariable("DB_USER");

        String password =
                getRequiredEnvironmentVariable("DB_PASSWORD");

        String url = String.format(
                "jdbc:mysql://%s:%s/%s"
                + "?useSSL=true"
                + "&allowPublicKeyRetrieval=true"
                + "&serverTimezone=UTC",
                host,
                port,
                database);

        return DriverManager.getConnection(
                url,
                username,
                password);
    }

    /**
     * Loads the MySQL JDBC driver.
     *
     * @throws SQLException when the driver cannot be loaded
     */
    private static void loadDatabaseDriver()
            throws SQLException {

        try {
            Class.forName(MYSQL_DRIVER_CLASS);
        } catch (ClassNotFoundException exception) {
            throw new SQLException(
                    "MySQL JDBC driver could not be loaded.",
                    exception);
        }
    }

    /**
     * Returns a required environment variable.
     *
     * @param variableName environment-variable name
     * @return configured environment-variable value
     */
    static String getRequiredEnvironmentVariable(
            final String variableName) {

        String value =
                System.getenv(variableName);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Required environment variable is "
                    + "missing: "
                    + variableName);
        }

        return value;
    }

    /**
     * Returns an environment variable or its default value.
     *
     * @param variableName environment-variable name
     * @param defaultValue default value
     * @return configured value or default value
     */
    static String getEnvironmentVariable(
            final String variableName,
            final String defaultValue) {

        String value =
                System.getenv(variableName);

        if (value == null || value.isBlank()) {
            return defaultValue;
        }

        return value;
    }
}
