package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto;
public record ClienteResponse(
        String id,
        String name,
        String email,
        String role,
        String status,
        String barrio,
        String calle,
        String city) {}