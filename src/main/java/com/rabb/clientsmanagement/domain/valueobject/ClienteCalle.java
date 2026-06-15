package com.rabb.clientsmanagement.domain.valueobject;

import com.rabb.clientsmanagement.domain.exception.InvalidClienteCalleException;

import java.util.Objects;

public record ClienteCalle(String value) {

  private static final int MINIMUM_LENGTH = 3;

  public ClienteCalle {
    final String normalizedValue = Objects.requireNonNull(value, "ClienteCalle cannot be null").trim();
    validateNotEmpty(normalizedValue);
    validateMinimumLength(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidClienteCalleException.becauseValueIsEmpty();
    }
  }

  private static void validateMinimumLength(final String normalizedValue) {
    if (normalizedValue.length() < MINIMUM_LENGTH) {
      throw InvalidClienteCalleException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
