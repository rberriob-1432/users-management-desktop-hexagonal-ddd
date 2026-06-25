package com.rabb.clientsmanagement.application.service;
import com.rabb.clientsmanagement.application.port.in.GetClientesByCalleAndCiudadUseCase;
import com.rabb.clientsmanagement.application.port.out.GetClientesByCalleAndCiudadPort;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetClientesByCalleAndCiudadService implements GetClientesByCalleAndCiudadUseCase {
    private final GetClientesByCalleAndCiudadPort getClientesByCalleAndCiudadPort;

    @Override
    public List<ClienteModel> execute(final String calle, final String ciudad) {
        return getClientesByCalleAndCiudadPort.getByCalleAndCiudad(calle, ciudad);
    }
}