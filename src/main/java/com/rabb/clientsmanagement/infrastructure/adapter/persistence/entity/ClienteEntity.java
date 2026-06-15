package com.rabb.clientsmanagement.infrastructure.adapter.persistence.entity;

public record ClienteEntity(
        String id,
        String name,
        String email,
        String password,
        String role,
        String status,
        String barrio,
        String calle,
        String city,
        String createdAt,
        String updatedAt) {}