package com.rabb.clientsmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record DeleteClienterCommand(
    @NotBlank(message = "id must not be blank") String id
) {

}
