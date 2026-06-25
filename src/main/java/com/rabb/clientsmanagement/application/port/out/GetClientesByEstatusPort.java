package com.rabb.clientsmanagement.application.port.out;

import com.rabb.clientsmanagement.domain.model.ClienteModel;

import java.util.List;

public interface GetClientesByEstatusPort {
    List<ClienteModel> getByEstatus(String status);
}