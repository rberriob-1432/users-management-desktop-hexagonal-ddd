package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto;

public record LoginRequest(
    String email,
    String password) {}
