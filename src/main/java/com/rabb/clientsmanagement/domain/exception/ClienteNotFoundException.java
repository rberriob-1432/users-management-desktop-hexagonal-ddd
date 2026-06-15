package com.rabb.clientsmanagement.domain.exception;

public final class ClienteNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "The client with id '%s' was not found.";

  private ClienteNotFoundException(final String message) {
    super(message);
  }

  public static ClienteNotFoundException becauseIdWasNotFound(final String clientId) {
    return new ClienteNotFoundException(String.format(MESSAGE_BY_ID, clientId));
  }
}
