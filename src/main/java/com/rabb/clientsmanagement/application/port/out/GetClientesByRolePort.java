package com.rabb.clientsmanagement.application.port.out;

import com.rabb.clientsmanagement.domain.model.ClienteModel;

import java.util.List;

public interface GetClientesByRolePort {

    List<ClienteModel> getByRole(String role);

}