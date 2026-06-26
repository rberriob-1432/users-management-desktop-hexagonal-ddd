package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.GetAllBarriosByCiudadUseCase;
import com.rabb.clientsmanagement.application.port.out.GetAllBarriosByCiudadPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetAllBarriosByCiudadService implements GetAllBarriosByCiudadUseCase {
    private final GetAllBarriosByCiudadPort getAllBarriosByCiudadPort;

    @Override
    public List<String> execute(final String ciudad) {
        return getAllBarriosByCiudadPort.getAllBarriosByCiudad(ciudad);
    }
}