package com.rabb.clientsmanagement.application.port.in;

import com.rabb.clientsmanagement.application.service.dto.command.CreateClienteCommand;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateClienteUseCase {
  ClienteModel execute(@NotNull @Valid CreateClienteCommand command);
}
