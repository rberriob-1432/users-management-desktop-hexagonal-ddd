package com.rabb.clientsmanagement.domain.exception;

public final class InvalidClienteNameException extends DomainException {

  private static final String MESSAGE_EMPTY = "The client name must not be empty.";
  private static final String MESSAGE_TOO_SHORT = "The client name must have at least %d characters.";

  private InvalidClienteNameException(final String message) {
    super(message);
  }

  public static InvalidClienteNameException becauseValueIsEmpty() {
    return new InvalidClienteNameException(MESSAGE_EMPTY);
  }

  public static InvalidClienteNameException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidClienteNameException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
