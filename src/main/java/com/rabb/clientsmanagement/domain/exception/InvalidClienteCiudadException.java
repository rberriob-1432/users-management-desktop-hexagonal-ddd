package com.rabb.clientsmanagement.domain.exception;

public final class InvalidClienteCiudadException extends DomainException {

  private static final String MESSAGE_EMPTY = "The client city must not be empty.";
  private static final String MESSAGE_TOO_SHORT = "The client city must have at least %d characters.";

  private InvalidClienteCiudadException(final String message) {
    super(message);
  }

  public static InvalidClienteCiudadException becauseValueIsEmpty() {
    return new InvalidClienteCiudadException(MESSAGE_EMPTY);
  }

  public static InvalidClienteCiudadException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidClienteCiudadException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
