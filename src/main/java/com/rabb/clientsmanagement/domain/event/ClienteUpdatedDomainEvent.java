package com.rabb.clientsmanagement.domain.event;

import java.util.Map;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import lombok.Getter;

@Getter
public final class ClienteUpdatedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "cliente.updated";

  private final ClienteModel cliente;

  public ClienteUpdatedDomainEvent(final ClienteModel cliente) {
    super(EVENT_NAME);
    this.cliente = cliente;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of(
            "id", cliente.getId().value(),
            "name", cliente.getName().value(),
            "email", cliente.getEmail().value(),
            "role", cliente.getRole().name(),
            "status", cliente.getStatus().name(),
            "barrio", cliente.getBarrio().value(),
            "calle", cliente.getCalle().value(),
            "city", cliente.getCiudad().value());
  }
}