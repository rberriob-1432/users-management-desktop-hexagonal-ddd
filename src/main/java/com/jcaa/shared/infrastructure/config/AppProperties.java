package com.jcaa.shared.infrastructure.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class AppProperties {

    private static final String PROPERTIES_FILE = "application.properties";

    private final Properties properties;

    public AppProperties() {
        this(AppProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
    }

    public AppProperties(final InputStream stream) {
        this.properties = doLoad(stream);
    }

    private static Properties doLoad(final InputStream stream) {
        Objects.requireNonNull(stream, "File not found in classpath: " + PROPERTIES_FILE);
        final Properties props = new Properties();
        try (stream) {
            props.load(stream);
        } catch (final IOException exception) {
            throw ConfigurationException.becauseLoadFailed(exception);
        }
        return props;
    }

    public String get(final String key) {
        // 1. Intentar obtener de variables de entorno (específico para Docker)
        // Convertimos db.host -> DB_HOST
        final String envKey = key.toUpperCase().replace(".", "_");
        final String envValue = System.getenv(envKey);
        if (envValue != null) {
            return envValue;
        }

        // 2. Intentar obtener del archivo properties
        final String value = properties.getProperty(key);
        Objects.requireNonNull(value, "Property not found in " + PROPERTIES_FILE + ": " + key);
        return value;
    }

    public int getInt(final String key) {
        return Integer.parseInt(get(key));
    }
}