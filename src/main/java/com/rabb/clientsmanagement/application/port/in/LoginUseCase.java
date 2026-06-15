package com.rabb.clientsmanagement.application.port.in;
import com.rabb.clientsmanagement.application.service.dto.command.LoginCommand;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface LoginUseCase {
  ClienteModel execute(@NotNull @Valid LoginCommand command);
}
