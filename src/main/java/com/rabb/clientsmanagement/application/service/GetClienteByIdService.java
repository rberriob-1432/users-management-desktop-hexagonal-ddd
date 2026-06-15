package com.rabb.clientsmanagement.application.service;
import com.rabb.clientsmanagement.application.port.in.GetClienteByIdUseCase;
import com.rabb.clientsmanagement.application.port.out.GetClienteByIdPort;
import com.rabb.clientsmanagement.application.service.dto.query.GetClienteByIdQuery;
import com.rabb.clientsmanagement.application.service.mapper.ClienteApplicationMapper;
import com.rabb.clientsmanagement.domain.exception.ClienteNotFoundException;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import com.rabb.clientsmanagement.domain.valueobject.ClienteId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class GetClienteByIdService implements GetClienteByIdUseCase {

  private final GetClienteByIdPort getClienteByIdPort;
  private final Validator validator;

  @Override
  public ClienteModel execute(final GetClienteByIdQuery query) {
    validateQuery(query);

    final ClienteId userId = ClienteApplicationMapper.fromGetClienteByIdQueryToClienteId(query);
    return getClienteByIdPort
        .getById(userId)
        .orElseThrow(() -> ClienteNotFoundException.becauseIdWasNotFound(userId.value()));
  }

  private void validateQuery(final GetClienteByIdQuery query) {
    final Set<ConstraintViolation<GetClienteByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
