package com.rabb.clientsmanagement.application.service;
import com.rabb.clientsmanagement.application.port.in.GetClientesByBarrioAndCiudadUseCase;
import com.rabb.clientsmanagement.application.port.out.GetClientesByBarrioAndCiudadPort;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetClientesByBarrioAndCiudadService implements GetClientesByBarrioAndCiudadUseCase {
    private final GetClientesByBarrioAndCiudadPort getClientesByBarrioAndCiudadPort;

    @Override
    public List<ClienteModel> execute(final String barrio, final String ciudad) {
        return getClientesByBarrioAndCiudadPort.getByBarrioAndCiudad(barrio, ciudad);
    }
}