package com.rabb.clientsmanagement.domain.valueobject;

import com.rabb.clientsmanagement.domain.exception.InvalidClienteNameException;

import java.util.Objects;

public record ClienteName(String value) {

  private static final int MINIMUM_LENGTH = 3;

  public ClienteName {
    final String normalizedValue = Objects.requireNonNull(value, "ClienteName cannot be null").trim();
    validateNotEmpty(normalizedValue);
    validateMinimumLength(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidClienteNameException.becauseValueIsEmpty();
    }
  }

  private static void validateMinimumLength(final String normalizedValue) {
    if (normalizedValue.length() < MINIMUM_LENGTH) {
      throw InvalidClienteNameException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
