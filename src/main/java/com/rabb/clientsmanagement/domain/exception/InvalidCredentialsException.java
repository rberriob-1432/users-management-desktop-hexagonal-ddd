package com.rabb.clientsmanagement.domain.exception;

public final class InvalidCredentialsException extends DomainException {

  private static final String MESSAGE_INVALID_CREDENTIALS = "Correo o contraseña incorrectos.";
  private static final String MESSAGE_LCIENTE_NOT_ACTIVE =
      "Tu cuenta no está activa. Contacta al administrador.";

  private InvalidCredentialsException(final String message) {
    super(message);
  }

  public static InvalidCredentialsException becauseCredentialsAreInvalid() {
    return new InvalidCredentialsException(MESSAGE_INVALID_CREDENTIALS);
  }

  public static InvalidCredentialsException becauseClienteIsNotActive() {
    return new InvalidCredentialsException(MESSAGE_LCIENTE_NOT_ACTIVE);
  }
}
