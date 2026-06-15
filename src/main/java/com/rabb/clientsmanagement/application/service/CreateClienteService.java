package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.CreateClienteUseCase;
import com.rabb.clientsmanagement.application.port.out.GetClienteByEmailPort;
import com.rabb.clientsmanagement.application.port.out.SaveClientePort;
import com.rabb.clientsmanagement.application.service.dto.command.CreateClienteCommand;
import com.rabb.clientsmanagement.application.service.mapper.ClienteApplicationMapper;
import com.rabb.clientsmanagement.domain.exception.ClienteAlreadyExistsException;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.valueobject.ClienteEmail;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Set;
import jakarta.validation.ConstraintViolation;

@Log
@RequiredArgsConstructor
public final class CreateClienteService implements CreateClienteUseCase {

  private final SaveClientePort saveClientePort;
  private final GetClienteByEmailPort getClienteByEmailPort;
  private final EmailNotificationService emailNotificationService;
  private final Validator validator;

  @Override
  public ClienteModel execute(final CreateClienteCommand command) {
    validateCommand(command);

    final ClienteEmail email = new ClienteEmail(command.email());
    ensureEmailIsNotTaken(email);

    final ClienteModel userToSave = ClienteApplicationMapper.fromCreateCommandToModel(command);
    final ClienteModel savedCliente = saveClientePort.save(userToSave);

    emailNotificationService.notifyClienteCreated(savedCliente, command.password());

    return savedCliente;
  }

  private void validateCommand(final CreateClienteCommand command) {
    final Set<ConstraintViolation<CreateClienteCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureEmailIsNotTaken(final ClienteEmail email) {
    getClienteByEmailPort
        .getByEmail(email)
        .ifPresent(
            ignored -> {
              throw ClienteAlreadyExistsException.becauseEmailAlreadyExists(email.value());
            });
  }
}
