package com.rabb.clientsmanagement.application.port.out;

import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.valueobject.ClienteEmail;

import java.util.Optional;

public interface GetClienteByEmailPort {
  Optional<ClienteModel> getByEmail(ClienteEmail email);
}
