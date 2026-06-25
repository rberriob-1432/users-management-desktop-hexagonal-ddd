package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.GetAllCallesByCiudadUseCase;
import com.rabb.clientsmanagement.application.port.out.GetAllCallesByCiudadPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetAllCallesByCiudadService implements GetAllCallesByCiudadUseCase {
    private final GetAllCallesByCiudadPort getAllCallesByCiudadPort;

    @Override
    public List<String> execute(final String ciudad) {
        return getAllCallesByCiudadPort.getAllCallesByCiudad(ciudad);
    }
}