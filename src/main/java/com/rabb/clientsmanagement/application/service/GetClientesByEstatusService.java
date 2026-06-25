package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.GetClientesByEstatusUseCase;
import com.rabb.clientsmanagement.application.port.out.GetClientesByEstatusPort;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetClientesByEstatusService implements GetClientesByEstatusUseCase {
    private final GetClientesByEstatusPort getClientesByEstatusPort;

    @Override
    public List<ClienteModel> execute(final String status) {
        return getClientesByEstatusPort.getByEstatus(status);
    }
}