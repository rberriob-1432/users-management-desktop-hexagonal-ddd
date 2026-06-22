package com.jcaa.shared.infrastructure.persistence;

import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnectionFactory {

    private DatabaseConnectionFactory() {}

    public static Connection createConnection(final DatabaseConfig config) {
        final String url = String.format("jdbc:postgresql://%s:%d/%s",
                config.host(), config.port(), config.database());
        try {
            return DriverManager.getConnection(url, config.username(), config.password());
        } catch (final SQLException exception) {
            throw PersistenceException.becauseConnectionFailed(exception);
        }
    }
}