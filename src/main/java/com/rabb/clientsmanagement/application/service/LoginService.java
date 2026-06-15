package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.LoginUseCase;
import com.rabb.clientsmanagement.application.port.out.GetClienteByEmailPort;
import com.rabb.clientsmanagement.application.service.dto.command.LoginCommand;
import com.rabb.clientsmanagement.domain.enums.ClienteStatus;
import com.rabb.clientsmanagement.domain.exception.InvalidCredentialsException;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.valueobject.ClienteEmail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class LoginService implements LoginUseCase {

  private final GetClienteByEmailPort getClienteByEmailPort;
  private final Validator validator;

  @Override
  public ClienteModel execute(final LoginCommand command) {
    validateCommand(command);

    final ClienteEmail email = new ClienteEmail(command.email());
    final ClienteModel user = findClienteOrFailWithInvalidCredentials(email);

    verifyPasswordOrFail(command.password(), user);
    ensureClienteIsActiveOrFail(user);

    return user;
  }

  private void validateCommand(final LoginCommand command) {
    final Set<ConstraintViolation<LoginCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private ClienteModel findClienteOrFailWithInvalidCredentials(final ClienteEmail email) {
    return getClienteByEmailPort
        .getByEmail(email)
        .orElseThrow(InvalidCredentialsException::becauseCredentialsAreInvalid);
  }

  private static void verifyPasswordOrFail(final String plainPassword, final ClienteModel user) {
    if (!user.getPassword().verifyPlain(plainPassword)) {
      throw InvalidCredentialsException.becauseCredentialsAreInvalid();
    }
  }

  private static void ensureClienteIsActiveOrFail(final ClienteModel user) {
    if (user.getStatus() != ClienteStatus.ACTIVE) {
      throw InvalidCredentialsException.becauseClienteIsNotActive();
    }
  }
}
