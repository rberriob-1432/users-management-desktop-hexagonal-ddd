package com.rabb.clientsmanagement.application.service;
import com.rabb.clientsmanagement.application.port.in.GetAllClientesUseCase;
import com.rabb.clientsmanagement.application.port.out.GetAllClientesPort;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetAllClientesService implements GetAllClientesUseCase {

  private final GetAllClientesPort getAllClientesPort;

  @Override
  public List<ClienteModel> execute() {
    return getAllClientesPort.getAll();
  }
}
