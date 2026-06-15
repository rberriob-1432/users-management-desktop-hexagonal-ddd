package com.rabb.clientsmanagement.domain.exception;
public final class ClienteAlreadyExistsException extends DomainException {

  private static final String MESSAGE_EMAIL_EXISTS = "A client with email '%s' already exists.";

  private ClienteAlreadyExistsException(final String message) {
    super(message);
  }

  public static ClienteAlreadyExistsException becauseEmailAlreadyExists(final String email) {
    return new ClienteAlreadyExistsException(String.format(MESSAGE_EMAIL_EXISTS, email));
  }
}
