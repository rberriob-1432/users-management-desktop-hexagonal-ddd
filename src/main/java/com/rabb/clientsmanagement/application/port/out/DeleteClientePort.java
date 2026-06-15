package com.rabb.clientsmanagement.application.port.out;

import com.rabb.clientsmanagement.domain.valueobject.ClienteId;

public interface DeleteClientePort {
  void delete(ClienteId userId);
}
