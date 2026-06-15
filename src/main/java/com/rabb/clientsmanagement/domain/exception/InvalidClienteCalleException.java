package com.rabb.clientsmanagement.domain.exception;

public final class InvalidClienteCalleException extends DomainException {

  private static final String MESSAGE_EMPTY = "The user street must not be empty.";
  private static final String MESSAGE_TOO_SHORT = "The user street must have at least %d characters.";

  private InvalidClienteCalleException(final String message) {
    super(message);
  }

  public static InvalidClienteCalleException becauseValueIsEmpty() {
    return new InvalidClienteCalleException(MESSAGE_EMPTY);
  }

  public static InvalidClienteCalleException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidClienteCalleException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
