package com.rabb.clientsmanagement.application.port.in;

import com.rabb.clientsmanagement.domain.model.ClienteModel;

import java.util.List;

public interface GetClientesByNameUseCase {
    List<ClienteModel> execute(String name);
}