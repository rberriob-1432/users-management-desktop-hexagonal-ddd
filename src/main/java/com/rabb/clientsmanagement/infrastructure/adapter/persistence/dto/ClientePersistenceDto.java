package com.rabb.clientsmanagement.infrastructure.adapter.persistence.dto;

public record ClientePersistenceDto(
        String id,
        String name,
        String email,
        String password,
        String role,
        String status,
        String calle,
        String barrio,
        String ciudad
) {}