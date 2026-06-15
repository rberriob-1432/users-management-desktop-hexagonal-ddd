package com.rabb.clientsmanagement.domain.event;

import java.util.Map;

import com.rabb.clientsmanagement.domain.valueobject.ClienteId;
import lombok.Getter;

@Getter
public final class ClienteDeletedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "cliente.deleted";

  private final ClienteId clienteId;

  public ClienteDeletedDomainEvent(final ClienteId clienteId) {
    super(EVENT_NAME);
    this.clienteId = clienteId;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of("id", clienteId.value());
  }
}
