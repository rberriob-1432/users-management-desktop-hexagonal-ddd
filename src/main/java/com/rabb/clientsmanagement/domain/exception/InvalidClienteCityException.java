package com.rabb.clientsmanagement.domain.exception;

public final class InvalidClienteCityException extends DomainException {

  private static final String MESSAGE_EMPTY = "The client city must not be empty.";
  private static final String MESSAGE_TOO_SHORT = "The client city must have at least %d characters.";

  private InvalidClienteCityException(final String message) {
    super(message);
  }

  public static InvalidClienteCityException becauseValueIsEmpty() {
    return new InvalidClienteCityException(MESSAGE_EMPTY);
  }

  public static InvalidClienteCityException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidClienteCityException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
