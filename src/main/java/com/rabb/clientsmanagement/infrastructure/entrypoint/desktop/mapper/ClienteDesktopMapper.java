package com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.mapper;

import com.rabb.clientsmanagement.application.service.dto.command.CreateClienteCommand;
import com.rabb.clientsmanagement.application.service.dto.command.DeleteClienteCommand;
import com.rabb.clientsmanagement.application.service.dto.command.LoginCommand;
import com.rabb.clientsmanagement.application.service.dto.command.UpdateClienteCommand;
import com.rabb.clientsmanagement.application.service.dto.query.GetClienteByIdQuery;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.ClienteResponse;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.CreateClienteRequest;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.LoginRequest;
import com.rabb.clientsmanagement.infrastructure.entrypoint.desktop.dto.UpdateClienteRequest;

import java.util.List;

public final class ClienteDesktopMapper {

  private ClienteDesktopMapper() {}

  public static CreateClienteCommand toCreateCommand(final CreateClienteRequest request) {
    return new CreateClienteCommand(
            request.id(),
            request.name(),
            request.email(),
            request.password(),
            request.role(),
            request.barrio(),
            request.calle(),
            request.city());
  }

  public static UpdateClienteCommand toUpdateCommand(final UpdateClienteRequest request) {
    return new UpdateClienteCommand(
            request.id(),
            request.name(),
            request.barrio(),
            request.calle(),
            request.city(),
            request.email(),
            request.password(),
            request.role(),
            request.status()
    );
  }



  public static ClienteResponse toResponse(final ClienteModel cliente) {//Relacionado
    return new ClienteResponse(
            cliente.getId().value(),
            cliente.getName().value(),
            cliente.getEmail().value(),
            cliente.getRole().name(),
            cliente.getStatus().name(),
            cliente.getBarrio().value(),
            cliente.getCalle().value(),
            cliente.getCity().value());
  }

  public static List<ClienteResponse> toResponseList(final List<ClienteModel> clientes) {
    return clientes.stream().map(ClienteDesktopMapper::toResponse).toList();
  }
  public static GetClienteByIdQuery toGetByIdQuery(final String id) {
    return new GetClienteByIdQuery(id);
  }

  public static DeleteClienteCommand toDeleteCommand(final String id) {
    return new DeleteClienteCommand(id);
  }

  public static LoginCommand toLoginCommand(final LoginRequest request) {
    return new LoginCommand(request.email(), request.password());
  }
}
