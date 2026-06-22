package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateClienteRequest(
        String id,
        String name,
        String email,
        String role,
        String status,
        String calle,
        String barrio,
        String password,
        String ciudad) {}