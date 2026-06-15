package com.rabb.clientsmanagement.domain.exception;

public final class InvalidClienteBarrioException extends DomainException {

  private static final String MESSAGE_EMPTY = "The user neighborhood must not be empty.";
  private static final String MESSAGE_TOO_SHORT = "The user neighborhood must have at least %d characters.";

  private InvalidClienteBarrioException(final String message) {
    super(message);
  }

  public static InvalidClienteBarrioException becauseValueIsEmpty() {
    return new InvalidClienteBarrioException(MESSAGE_EMPTY);
  }

  public static InvalidClienteBarrioException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidClienteBarrioException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
