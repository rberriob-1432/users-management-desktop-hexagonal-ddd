package com.rabb.clientsmanagement.application.service.mapper;

import java.util.Objects;
import com.rabb.clientsmanagement.application.service.dto.command.*;
import com.rabb.clientsmanagement.application.service.dto.query.GetClienteByIdQuery;
import com.rabb.clientsmanagement.domain.enums.ClienteRole;
import com.rabb.clientsmanagement.domain.enums.ClienteStatus;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.valueobject.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClienteApplicationMapper {

  public ClienteModel fromCreateCommandToModel(final CreateClienteCommand command) {
    return ClienteModel.create(
            new ClienteId(command.id()),
            new ClienteName(command.name()),
            new ClienteEmail(command.email()),
            ClientePassword.fromPlainText(command.password()),
            ClienteRole.fromString(command.role()),
            new ClienteBarrio(command.barrio()),//Error
            new ClienteCalle(command.calle()),//Error
            new ClienteCity(command.city()));
  }

  public ClienteModel fromUpdateCommandToModel(
          final UpdateClienteCommand command, final ClientePassword currentPassword) {

    final ClientePassword passwordToCliente = resolvePassword(command.password(), currentPassword);

    return new ClienteModel(
            new ClienteId(command.id()),
            new ClienteName(command.name()),
            new ClienteEmail(command.email()),
            passwordToCliente,
            ClienteRole.fromString(command.role()),
            ClienteStatus.fromString(command.status()),
            new ClienteBarrio(command.barrio()),
            new ClienteCalle(command.calle()),
            new ClienteCity(command.city()));
  }

  public ClienteId fromGetClienteByIdQueryToClienteId(final GetClienteByIdQuery query) {
    return new ClienteId(query.id());
  }

  public ClienteId fromDeleteCommandToClienteId(final DeleteClienteCommand command) {
    return new ClienteId(command.id());
  }

  private ClientePassword resolvePassword(
          final String newPlainPassword, final ClientePassword currentPassword) {
    if (Objects.isNull(newPlainPassword) || newPlainPassword.isBlank()) {
      return currentPassword;
    }
    return ClientePassword.fromPlainText(newPlainPassword);
  }
}