package com.jcaa.shared.infrastructure.persistence;

public record DatabaseConfig(
        String host,
        int port,
        String database,
        String username,
        String password
) {}