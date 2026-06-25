package com.rabb.clientsmanagement.application.port.out;

import com.rabb.clientsmanagement.domain.model.ClienteModel;

import java.util.List;

public interface GetClientesByCalleAndCiudadPort {
    List<ClienteModel> getByCalleAndCiudad(String calle, String ciudad);
}