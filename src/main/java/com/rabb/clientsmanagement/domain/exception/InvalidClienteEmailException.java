package com.rabb.clientsmanagement.domain.exception;

public final class InvalidClienteEmailException extends DomainException {

  private static final String MESSAGE_EMPTY = "The client email must not be empty.";
  private static final String MESSAGE_INVALID_FORMAT = "The client email format is invalid: '%s'.";

  private InvalidClienteEmailException(final String message) {
    super(message);
  }

  public static InvalidClienteEmailException becauseValueIsEmpty() {
    return new InvalidClienteEmailException(MESSAGE_EMPTY);
  }

  public static InvalidClienteEmailException becauseFormatIsInvalid(final String email) {
    return new InvalidClienteEmailException(String.format(MESSAGE_INVALID_FORMAT, email));
  }
}
