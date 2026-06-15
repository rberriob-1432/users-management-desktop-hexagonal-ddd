package com.rabb.clientsmanagement.application.port.in;

import com.rabb.clientsmanagement.application.service.dto.query.GetClienteByIdQuery;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetClienteByIdUseCase {
  ClienteModel execute(@NotNull @Valid GetClienteByIdQuery query);
}
