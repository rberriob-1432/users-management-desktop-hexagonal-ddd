package com.rabb.clientsmanagement.domain.exception;
public final class InvalidClientePasswordException extends DomainException {

  private static final String MESSAGE_EMPTY = "The client password must not be empty.";
  private static final String MESSAGE_TOO_SHORT =
      "The client password must have at least %d characters.";

  private InvalidClientePasswordException(final String message) {
    super(message);
  }

  public static InvalidClientePasswordException becauseValueIsEmpty() {
    return new InvalidClientePasswordException(MESSAGE_EMPTY);
  }

  public static InvalidClientePasswordException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidClientePasswordException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
