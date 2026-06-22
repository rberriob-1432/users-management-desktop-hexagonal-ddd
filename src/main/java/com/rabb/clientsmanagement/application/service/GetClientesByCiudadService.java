package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.GetClientesByCiudadUseCase;
import com.rabb.clientsmanagement.application.port.out.GetClientesByCiudadPort;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetClientesByCiudadService implements GetClientesByCiudadUseCase {
    private final GetClientesByCiudadPort getClientesByCiudadPort;

    @Override
    public List<ClienteModel> execute(final String ciudad) {
        return getClientesByCiudadPort.getByCiudad(ciudad);
    }
}