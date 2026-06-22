package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.UpdateClienteUseCase;
import com.rabb.clientsmanagement.application.port.out.GetClienteByEmailPort;
import com.rabb.clientsmanagement.application.port.out.GetClienteByIdPort;
import com.rabb.clientsmanagement.application.port.out.UpdateClientePort;
import com.rabb.clientsmanagement.application.service.dto.command.UpdateClienteCommand;
import com.rabb.clientsmanagement.application.service.mapper.ClienteApplicationMapper;
import com.rabb.clientsmanagement.domain.exception.ClienteAlreadyExistsException;
import com.rabb.clientsmanagement.domain.exception.ClienteNotFoundException;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.valueobject.ClienteEmail;
import com.rabb.clientsmanagement.domain.valueobject.ClienteId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class UpdateClienteService implements UpdateClienteUseCase {

    private final UpdateClientePort updateClientePort;
    private final GetClienteByIdPort getClienteByIdPort;
    private final GetClienteByEmailPort getClienteByEmailPort;
    private final EmailNotificationServiceCliente emailNotificationServiceCliente;
    private final Validator validator;

    @Override
    public ClienteModel execute(final UpdateClienteCommand command) {
        validateCommand(command);

        final ClienteId userId = new ClienteId(command.id());
        final ClienteModel current = findExistingClienteOrFail(userId);
        final ClienteEmail newEmail = new ClienteEmail(command.email());

        ensureEmailIsNotTakenByAnotherCliente(newEmail, userId);

        final ClienteModel userToUpdate =
                ClienteApplicationMapper.fromUpdateCommandToModel(command, current.getPassword().value());
        final ClienteModel updatedCliente = updateClientePort.update(userToUpdate);

        emailNotificationServiceCliente.notifyClienteUpdated(updatedCliente);

        return updatedCliente;
    }

    private void validateCommand(final UpdateClienteCommand command) {
        final Set<ConstraintViolation<UpdateClienteCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private ClienteModel findExistingClienteOrFail(final ClienteId userId) {
        return getClienteByIdPort
                .getById(userId)
                .orElseThrow(() -> ClienteNotFoundException.becauseIdWasNotFound(userId.value()));
    }

    private void ensureEmailIsNotTakenByAnotherCliente(final ClienteEmail newEmail, final ClienteId ownerId) {
        getClienteByEmailPort
                .getByEmail(newEmail)
                .ifPresent(
                        found -> {
                            if (!found.getId().equals(ownerId)) {
                                throw ClienteAlreadyExistsException.becauseEmailAlreadyExists(newEmail.value());
                            }
                        });
    }
}