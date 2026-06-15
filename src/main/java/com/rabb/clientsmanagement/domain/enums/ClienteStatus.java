package com.rabb.clientsmanagement.domain.enums;
import com.rabb.clientsmanagement.domain.exception.InvalidClienteStatusException;
public enum ClienteStatus {
  ACTIVE,
  INACTIVE,
  PENDING,
  BLOCKED;

  public static ClienteStatus fromString(final String value) {
    for (final ClienteStatus status : values()) {
      if (status.name().equalsIgnoreCase(value)) {
        return status;
      }
    }
    throw InvalidClienteStatusException.becauseValueIsInvalid(value);
  }
}
