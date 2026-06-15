package com.rabb.clientsmanagement.domain.valueobject;

import com.rabb.clientsmanagement.domain.exception.InvalidClienteIdException;

import java.util.Objects;

public record ClienteId(String value) {

  public ClienteId {
    final String normalizedValue = Objects.requireNonNull(value, "ClienteId cannot be null").trim();
    validateNotEmpty(normalizedValue);
    // asigna el valor normalizado al componente
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidClienteIdException.becauseValueIsEmpty();
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
