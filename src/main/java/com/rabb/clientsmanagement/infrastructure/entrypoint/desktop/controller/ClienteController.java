package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.controller;


import com.rabb.clientsmanagement.application.port.in.*;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.CreateClienteRequest;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.LoginRequest;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.UpdateClienteRequest;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.mapper.ClienteDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ClienteController {

  private final CreateClienteUseCase createClienteUseCase;
  private final UpdateClienteUseCase updateClienteUseCase;
  private final DeleteClienteUseCase deleteClienteUseCase;
  private final GetClienteByIdUseCase getClienteByIdUseCase;
  private final GetAllClientesUseCase getAllClientesUseCase;
  private final LoginUseCase loginUseCase;

  public List<ClienteResponse> listAllClientes() {
    final var clientes = getAllClientesUseCase.execute();
    return ClienteDesktopMapper.toResponseList(clientes);
  }

  public ClienteResponse findClienteById(final String id) {
    final var query = ClienteDesktopMapper.toGetByIdQuery(id);//eror
    final var user = getClienteByIdUseCase.execute(query);
    return ClienteDesktopMapper.toResponse(user);
  }

  public ClienteResponse createCliente(final CreateClienteRequest request) {
    final var command = ClienteDesktopMapper.toCreateCommand(request);
    final var user = createClienteUseCase.execute(command);
    return ClienteDesktopMapper.toResponse(user);
  }

  public ClienteResponse updateCliente(final UpdateClienteRequest request) {
    final var command = ClienteDesktopMapper.toUpdateCommand(request);
    final var user = updateClienteUseCase.execute(command);
    return ClienteDesktopMapper.toResponse(user);
  }

  public void deleteCliente(final String id) {
    final var command = ClienteDesktopMapper.toDeleteCommand(id);//eror
    deleteClienteUseCase.execute(command);
  }

  public ClienteResponse login(final LoginRequest request) {
    final var command = ClienteDesktopMapper.toLoginCommand(request);//eror
    final var user = loginUseCase.execute(command);
    return ClienteDesktopMapper.toResponse(user);
  }
}
