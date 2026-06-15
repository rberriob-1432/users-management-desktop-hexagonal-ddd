package com.rabb.clientsmanagement.application.port.in;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateClienteUseCase {
  ClienteModel execute(@NotNull @Valid UpdateClienteCommand command);
}
