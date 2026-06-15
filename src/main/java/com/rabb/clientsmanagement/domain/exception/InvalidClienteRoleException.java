package com.rabb.clientsmanagement.domain.exception;

public final class InvalidClienteRoleException extends DomainException {

  private static final String MESSAGE_INVALID = "The client role '%s' is not valid.";

  private InvalidClienteRoleException(final String message) {
    super(message);
  }

  public static InvalidClienteRoleException becauseValueIsInvalid(final String role) {
    return new InvalidClienteRoleException(String.format(MESSAGE_INVALID, role));
  }
}
