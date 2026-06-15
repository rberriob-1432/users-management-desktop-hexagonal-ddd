package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.DeleteClienteUseCase;
import com.rabb.clientsmanagement.application.port.out.DeleteClientePort;
import com.rabb.clientsmanagement.application.port.out.GetClienteByIdPort;
import com.rabb.clientsmanagement.application.service.dto.command.DeleteClienteCommand;
import com.rabb.clientsmanagement.application.service.mapper.ClienteApplicationMapper;
import com.rabb.clientsmanagement.domain.exception.ClienteNotFoundException;
import com.rabb.clientsmanagement.domain.valueobject.ClienteId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class DeleteClienteService implements DeleteClienteUseCase {

  private final DeleteClientePort deleteClientePort;
  private final GetClienteByIdPort getClienteByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteClienteCommand command) {
    validateCommand(command);

    final ClienteId userId = ClienteApplicationMapper.fromDeleteCommandToClienteId(command);
    ensureClienteExists(userId);
    deleteClientePort.delete(userId);
  }

  private void validateCommand(final DeleteClienteCommand command) {
    final Set<ConstraintViolation<DeleteClienteCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureClienteExists(final ClienteId userId) {
    getClienteByIdPort
        .getById(userId)
        .orElseThrow(() -> ClienteNotFoundException.becauseIdWasNotFound(userId.value()));
  }
}
