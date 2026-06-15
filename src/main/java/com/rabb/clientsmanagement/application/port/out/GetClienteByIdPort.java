package com.rabb.clientsmanagement.application.port.out;

import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.valueobject.ClienteId;

import java.util.Optional;

public interface GetClienteByIdPort {
  Optional<ClienteModel> getById(ClienteId userId);
}
