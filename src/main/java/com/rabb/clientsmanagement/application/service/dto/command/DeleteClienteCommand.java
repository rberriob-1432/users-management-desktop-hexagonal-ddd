package com.rabb.clientsmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record DeleteClienteCommand(
    @NotBlank(message = "id must not be blank") String id
) {

}
