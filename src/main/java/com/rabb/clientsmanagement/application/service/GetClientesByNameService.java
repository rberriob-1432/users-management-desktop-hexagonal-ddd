package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.GetClientesByNameUseCase;
import com.rabb.clientsmanagement.application.port.out.GetClientesByNamePort;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetClientesByNameService implements GetClientesByNameUseCase {
    private final GetClientesByNamePort getClientesByNamePort;

    @Override
    public List<ClienteModel> execute(final String name) {
        return getClientesByNamePort.getByName(name);
    }
}