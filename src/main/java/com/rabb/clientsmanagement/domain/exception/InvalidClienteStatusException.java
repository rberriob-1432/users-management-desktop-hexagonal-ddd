package com.rabb.clientsmanagement.domain.exception;
public final class InvalidClienteStatusException extends DomainException {

  private static final String MESSAGE_INVALID = "The client status '%s' is not valid.";

  private InvalidClienteStatusException(final String message) {
    super(message);
  }

  public static InvalidClienteStatusException becauseValueIsInvalid(final String status) {
    return new InvalidClienteStatusException(String.format(MESSAGE_INVALID, status));
  }
}
