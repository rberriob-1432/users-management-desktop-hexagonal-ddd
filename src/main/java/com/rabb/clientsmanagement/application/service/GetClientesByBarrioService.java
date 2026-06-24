package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.GetClientesByBarrioUseCase;
import com.rabb.clientsmanagement.application.port.out.GetClientesByBarrioPort;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetClientesByBarrioService implements GetClientesByBarrioUseCase {
    private final GetClientesByBarrioPort getClientesByBarrioPort;

    @Override
    public List<ClienteModel> execute(final String ciudad) {
        return getClientesByBarrioPort.getByBarrio(ciudad);
    }
}