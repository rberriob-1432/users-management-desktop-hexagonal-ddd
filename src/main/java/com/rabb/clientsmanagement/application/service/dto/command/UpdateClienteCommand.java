package com.rabb.clientsmanagement.application.service.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateClienteCommand(
        @NotBlank(message = "id must not be blank") String id,
        @NotBlank(message = "name must not be blank")
        @Size(min = 3, message = "name must have at least 3 characters")
        String name,
        @NotBlank(message = "neighborhood must not be blank")
        @Size(min = 3, message = "neighborhood must have at least 3 characters")
        String barrio,
        @NotBlank(message = "street must not be blank")
        @Size(min = 3, message = "street must have at least 3 characters")
        String calle,
        @NotBlank(message = "city must not be blank")
        @Size(min = 3, message = "city must have at least 3 characters")
        String city,
        @NotBlank(message = "email must not be blank")
        @Email(message = "email must be a valid email address")
        String email,
        @NotBlank(message = "password must not be blank")
        @Size(min = 8, message = "password must have at least 8 characters")
        String password,
        @NotBlank(message = "role must not be blank") String role)
{

}
