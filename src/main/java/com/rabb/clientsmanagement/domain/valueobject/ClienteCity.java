package com.rabb.clientsmanagement.domain.valueobject;

import com.rabb.clientsmanagement.domain.exception.InvalidClienteCityException;

import java.util.Objects;

public record ClienteCity(String value) {

  private static final int MINIMUM_LENGTH = 3;

  public ClienteCity {
    final String normalizedValue = Objects.requireNonNull(value, "ClienteCity cannot be null").trim();
    validateNotEmpty(normalizedValue);
    validateMinimumLength(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidClienteCityException.becauseValueIsEmpty();
    }
  }

  private static void validateMinimumLength(final String normalizedValue) {
    if (normalizedValue.length() < MINIMUM_LENGTH) {
      throw InvalidClienteCityException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
