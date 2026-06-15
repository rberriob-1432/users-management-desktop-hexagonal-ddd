package com.rabb.clientsmanagement.domain.exception;

public final class InvalidClienteIdException extends DomainException {

  private static final String MESSAGE_EMPTY = "The user id must not be empty.";

  private InvalidClienteIdException(final String message) {
    super(message);
  }

  public static InvalidClienteIdException becauseValueIsEmpty() {
    return new InvalidClienteIdException(MESSAGE_EMPTY);
  }
}
