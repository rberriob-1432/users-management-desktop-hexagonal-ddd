package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto;

public record ClienteResponse(
        String id,
        String name,
        String email,
        String password,
        String role,
        String barrio,
        String calle,
        String city) {}