package com.rabb.clientsmanagement.application.port.in;

import com.rabb.clientsmanagement.application.service.dto.command.DeleteClienteCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteClienteUseCase {
  void execute(@NotNull @Valid DeleteClienteCommand command);
}
