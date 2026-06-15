package com.rabb.clientsmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetClienteByIdQuery(@NotBlank(message = "id must not be blank") String id)
{

}
