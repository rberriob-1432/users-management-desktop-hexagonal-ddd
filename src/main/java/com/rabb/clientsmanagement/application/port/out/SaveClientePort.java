package com.rabb.clientsmanagement.application.port.out;

import com.rabb.clientsmanagement.domain.model.ClienteModel;

public interface SaveClientePort {
  ClienteModel save(ClienteModel user);
}
