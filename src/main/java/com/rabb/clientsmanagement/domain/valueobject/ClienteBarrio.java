package com.rabb.clientsmanagement.domain.valueobject;

import com.rabb.clientsmanagement.domain.exception.InvalidClienteBarrioException;

import java.util.Objects;

public record ClienteBarrio(String value) {

  private static final int MINIMUM_LENGTH = 3;

  public ClienteBarrio {
    final String normalizedValue = Objects.requireNonNull(value, "ClienteBarrio cannot be null").trim();
    validateNotEmpty(normalizedValue);
    validateMinimumLength(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidClienteBarrioException.becauseValueIsEmpty();
    }
  }

  private static void validateMinimumLength(final String normalizedValue) {
    if (normalizedValue.length() < MINIMUM_LENGTH) {
      throw InvalidClienteBarrioException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
