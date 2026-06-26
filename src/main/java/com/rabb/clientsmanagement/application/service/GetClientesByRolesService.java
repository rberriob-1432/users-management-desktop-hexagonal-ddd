package com.rabb.clientsmanagement.application.service;

import com.rabb.clientsmanagement.application.port.in.GetClientesByRoleUseCase;
import com.rabb.clientsmanagement.application.port.out.GetClientesByRolePort;
import com.rabb.clientsmanagement.domain.model.ClienteModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetClientesByRolesService implements GetClientesByRoleUseCase {
    private final GetClientesByRolePort getClientesByRolePort;

    @Override
    public List<ClienteModel> execute(final String status) {
        return getClientesByRolePort.getByRole(status);
    }
}